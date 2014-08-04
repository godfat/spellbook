
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

# You can use either PIXI.WebGLRenderer or PIXI.CanvasRenderer
renderer = new PIXI.WebGLRenderer 800, 600

document.body.appendChild(renderer.view)

stage = new PIXI.Stage

bunnyTexture = PIXI.Texture.fromImage("spiritual_soul.jpg")
bunny = new PIXI.Sprite(bunnyTexture)

bunny.position.x = 400
bunny.position.y = 300

bunny.scale.x = 2
bunny.scale.y = 2

stage.addChild(bunny)

animate = ->
  bunny.rotation += 0.01
  renderer.render(stage)
  requestAnimationFrame(animate)

animate!
