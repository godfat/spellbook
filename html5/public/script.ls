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
width  = 16
height = 10
hexw   = 64
hexh   = 55
hexwh  = hexw * 0.5
hexhh  = hexh * 0.5
hexwo  = hexw * 0.76

document.body.appendChild renderer.view

stage = new PIXI.Stage

hexagon = PIXI.Texture.fromImage "hexagon-small-transparent.gif"

gen = prelude.map (idx) ->
  hex = new PIXI.Sprite hexagon
  hex.position.x = hexwo * idx `prelude.mod` width
  hex.position.y = hexh  * idx `prelude.div` width -
                     if idx `prelude.mod` 2 === 0 then -hexhh else 0
  txt = new PIXI.Text idx.toString!
  txt.position.x = hexwh - txt.width  / 2
  txt.position.y = hexhh - txt.height / 2
  hex.addChild txt
  hex.interactive = true
  hex.click = -> console.log idx
  stage.addChild hex

map = gen [0 to width * height - 1]

animate = ->
  renderer.render stage
  requestAnimationFrame animate

# animate!
renderer.render stage
