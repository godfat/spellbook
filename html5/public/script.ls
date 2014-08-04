'use strict'

prelude = require 'prelude-ls'

createws = ->
  ws = new WebSocket "ws://#{location.host}/ws"

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

document.body.appendChild renderer.view

stage = new PIXI.Stage

hexagon = PIXI.Texture.fromImage "hexagon-small-transparent.gif"

gen = prelude.map (idx) ->
  hex = new PIXI.Sprite hexagon
  hex.position.x = 49 * idx `prelude.mod` width
  hex.position.y = 55 * idx `prelude.div` width -
                     if idx `prelude.mod` 2 === 0 then -27.5 else 0
  txt = new PIXI.Text idx.toString!
  txt.position.x = 32   - txt.width  / 2
  txt.position.y = 27.5 - txt.height / 2
  hex.addChild txt
  stage.addChild hex

map = gen [0 to width * height - 1]

animate = ->
  renderer.render stage
  requestAnimationFrame animate

animate!
