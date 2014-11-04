#!/usr/bin/env jruby --2.0 -J-cp /usr/local/Cellar/frege/3.21.500-g88270a0/libexec/frege3.21.500-g88270a0.jar:. -S puma -e none -p 8080

require 'digest/sha1'

require 'jellyfish'
require 'websocket_parser'

java_import 'spellbook.Hexagon'

class SpellbookServer
  include Jellyfish
  WSGUID = '258EAFA5-E914-47DA-95CA-C5AB0DC85B11'

  jruby_bug = Module.new{
    def nearby width, idx
      Java::Spellbook::Hexagon.nearby(width, idx).to_a
    end
  }

  controller_include jruby_bug, Jellyfish::WebSocket

  get '/ws' do
    switch_protocol do |msg|
      puts "on_message: #{msg}"
      ws_write(nearby(*msg.scan(/\d+/).map(&:to_i)).join(' '))
    end

    parser.on_error do |e|
      puts "on_error: #{e}"
      sock.close
    end

    parser.on_close do |status, message|
      puts "on_close: #{status}, #{message}"
      ws_close
    end

    parser.on_ping do |ping|
      puts "on_ping: #{ping}"
      sock << WebSocket::Message.pong(ping).to_data
    end

    Thread.new{ ws_start }
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
