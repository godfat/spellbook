'use strict'

# http://www.goodboydigital.com/pixijs/docs/
# http://livescript.net/
# http://www.preludels.com/

prelude = require 'prelude-ls'

settings =
  width: 16
  height: 10
  hexw: 64
  hexh: 55
  hexagon: PIXI.Texture.fromImage "hexagon-small-transparent.gif"

settings{hexwh, hexhh, hexwo} =
  hexwh: settings.hexw * 0.5
  hexhh: settings.hexh * 0.5
  hexwo: settings.hexw * 0.76

generate = (s, click) ->
  stage = new PIXI.Stage
  tiles = [0 to s.width * s.height - 1] `prelude.flip(prelude.map)` (idx) ->
    hex = new PIXI.Sprite s.hexagon
    hex.position.x = s.hexwo * idx `prelude.mod` s.width
    hex.position.y = s.hexh  * idx `prelude.div` s.width -
                       if idx `prelude.mod` 2 == 0 then -s.hexhh else 0
    txt = new PIXI.Text idx.toString!
    txt.position.x = s.hexwh - txt.width  / 2
    txt.position.y = s.hexhh - txt.height / 2
    hex.addChild txt
    hex.interactive = true
    hex.click = -> click s, idx
    stage.addChild hex
    hex
  [stage, tiles]

ws = void
wait = 1000
connect = (ts) ->
  ws := new WebSocket "ws://#{location.host}#{location.pathname}ws"

  ws.onopen = ->
    wait := 1000
    console.log "onopen"

  ws.onmessage = (msg) ->
    console.log "onmessage: #{msg.data}"
    indices = prelude.split ' ', msg.data
    console.log indices
    indices `prelude.flip(prelude.each)` (i) ->
      ts[parseInt(i)]?.tint += 0x123456

  ws.onerror = (e) ->
    wait *=: 2
    console.log "onerror: #{e}"

  ws.onclose = ->
    console.log "onclose, wait: #{wait}"
    ws := void
    wait `prelude.flip(setTimeout)` ->
      ws := connect ts

  ws

send = (msg) ->
  if ws
    console.log "send: #{msg}"
    ws.send msg
  else
    console.log "reconnect for: #{msg}"

[stage, tiles] = generate settings, (s, idx) ->
  send "nearby: #{idx} #{s.width}"

connect tiles

renderer = new PIXI.WebGLRenderer 800, 580
document.body.appendChild renderer.view

animate = ->
  renderer.render stage
  requestAnimationFrame animate

animate!
