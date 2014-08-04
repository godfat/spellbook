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

up = (s, idx) -> idx - s.width
left-up = (s, idx) -> if idx `prelude.mod` 2 == 0
                      then idx - 1
                      else up(s, idx) - 1
right-up = (s, idx) -> if idx `prelude.mod` 2 == 0
                       then idx + 1
                       else up(s, idx) + 1
down = (s, idx) -> idx + s.width
left-down = (s, idx) -> if idx `prelude.mod` 2 == 0
                        then down(s, idx) - 1
                        else idx - 1
right-down = (s, idx) -> if idx `prelude.mod` 2 == 0
                         then down(s, idx) + 1
                         else idx + 1
nearby = (s, idx) -> [left-up(s, idx), up(s, idx), right-up(s, idx),
                      left-down(s, idx), down(s, idx), right-down(s, idx)]

renderer = new PIXI.WebGLRenderer 800, 580
[stage, tiles] = generate settings, (s, idx) ->
  send "nearby: #{s.width} #{idx}"

createws = ->
  ws = new WebSocket "ws://#{location.host}#{location.pathname}ws"

  ws.onopen = ->
    console.log "onopen"

  ws.onmessage = (msg) ->
    console.log "onmessage: #{msg.data}"
    indices = prelude.split ' ', msg.data
    console.log indices
    indices `prelude.flip(prelude.each)` (i) ->
      tiles[parseInt(i)]?.tint += 0x123456

  ws.onerror = (e) ->
    console.log "onerror: #{e}"

  ws.onclose = ->
    console.log "onclose"
    createws! # how do we reconnect?

  ws

ws = createws!
send = (msg) ->
  console.log "send: #{msg}"
  ws.send msg

document.body.appendChild renderer.view

animate = ->
  renderer.render stage
  requestAnimationFrame animate

animate!
