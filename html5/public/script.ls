
init = ->
  connection = new WebSocket("ws://#{location.host}/ws")

  connection.onopen = ->
    console.log 'onopen'
    connection.send 'Ping'

  connection.onmessage = (msg) ->
    console.log "onmessage: #{msg.data}"

  connection.onerror = (e) ->
    console.log "onerror: #{e}"

  connection.onclose = ->
    console.log 'onclose'
    init!

init!

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
