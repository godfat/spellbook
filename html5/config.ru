#!/usr/bin/env rainbows -Ilib -Nc rainbows.rb

require 'digest/sha1'

require 'jellyfish'
require 'websocket_parser'

class SpellbookServer
  include Jellyfish
  WSGUID = '258EAFA5-E914-47DA-95CA-C5AB0DC85B11'

  controller_include Module.new{
    def switch_protocol
      key = env['HTTP_SEC_WEBSOCKET_KEY']
      accept = [Digest::SHA1.digest("#{key}#{WSGUID}")].pack('m').strip
      sock = env['rack.hijack'].call
      sock.binmode
      sock.write(<<-HTTP)
HTTP/1.1 101 Switching Protocols\r
Upgrade: websocket\r
Connection: Upgrade\r
Sec-WebSocket-Accept: #{accept}\r
\r
      HTTP
      sock
    end

    def create_ws sock
      parser = WebSocket::Parser.new
      parser.on_message do |msg|
        puts "on_message: #{msg}"
        sock << WebSocket::Message.new(msg).to_data
      end

      parser.on_error do |e|
        puts "on_error: #{e}"
        sock.close
      end

      parser.on_close do |status, message|
        puts "on_close: #{status}, #{message}"
        close(sock)
      end

      parser.on_ping do |ping|
        puts "on_ping: #{ping}"
        sock << WebSocket::Message.pong(ping).to_data
      end

      parser
    end

    def start sock, ws
      Thread.new do
        while !sock.closed? && IO.select([sock]) do
          ws << sock.readpartial(8192)
        end
      end
    end

    def close sock
      sock << WebSocket::Message.close.to_data
      sock.close
    end
  }

  get '/ws' do
    sock = switch_protocol
    ws   = create_ws(sock)
    start(sock, ws)
    ''
  end
end

App = Rack::Builder.app do
  use Rack::CommonLogger
  use Rack::Chunked
  use Rack::ContentLength
  use Rack::Deflater

  run Rack::Cascade.new([Rack::File.new('public/index.html'),
                         Rack::File.new('public'),
                         SpellbookServer.new])
end

run App
