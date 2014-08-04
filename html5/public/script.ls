'use strict'

# http://www.goodboydigital.com/pixijs/docs/
# http://livescript.net/
# http://www.preludels.com/

prelude = require 'prelude-ls'

createws = ->
  ws = new WebSocket "ws://#{location.host}#{location.pathname}ws"

  send = (msg) ->
    console.log "send: #{msg}"
    ws.send msg

  ws.onopen = ->
    console.log "onopen"
    send "Hi"

  ws.onmessage = (msg) ->
    console.log "onmessage: #{msg.data}"

  ws.onerror = (e) ->
    console.log "onerror: #{e}"

  ws.onclose = ->
    console.log "onclose"
    createws!

createws!

renderer = new PIXI.WebGLRenderer 800 580
s =
  width: 16
  height: 10
  hexw: 64
  hexh: 55
s{hexwh, hexhh, hexwo} =
  hexwh: s.hexw * 0.5
  hexhh: s.hexh * 0.5
  hexwo: s.hexw * 0.76

document.body.appendChild renderer.view

stage = new PIXI.Stage

hexagon = PIXI.Texture.fromImage "hexagon-small-transparent.gif"

gen = (s, click) ->
  [0 to s.width * s.height - 1] `prelude.flip(prelude.map)` (idx) ->
    hex = new PIXI.Sprite hexagon
    hex.position.x = s.hexwo * idx `prelude.mod` s.width
    hex.position.y = s.hexh  * idx `prelude.div` s.width -
                       if idx `prelude.mod` 2 === 0 then -s.hexhh else 0
    txt = new PIXI.Text idx.toString!
    txt.position.x = s.hexwh - txt.width  / 2
    txt.position.y = s.hexhh - txt.height / 2
    hex.addChild txt
    hex.interactive = true
    hex.click = -> click idx
    stage.addChild hex

map = gen s, console.~log

animate = ->
  renderer.render stage
  requestAnimationFrame animate

# animate!
renderer.render stage
