const vertexShaderText = 
`
precision mediump float;

attribute vec3 vertPosition;
attribute vec3 vertColor;
varying vec3 fragColor;

uniform mat4 _world;
uniform mat4 _view;
uniform mat4 _proj;

void main(){
   gl_Position = _proj * _view * _world * vec4(vertPosition,1.0);
   fragColor = vertColor;
}`;


const fragmentShaderText = `
    precision mediump float;
    varying vec3 fragColor;
    void main(){
       gl_FragColor = vec4(fragColor,1.0);
    }`;

function initTutorial(){
    console.log("Loaded! !");
    var canvas =  document.getElementById("work_surface");
    var gl = canvas.getContext('webgl'); 

    if(!gl){
        gl =  canvas.getContext('experimental-webgl');
    }

    if(!gl){
        alert("browser does not support webGL");
    }

    gl.clearColor(0.75, 0.75, 0.75, 1);
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    var vertexShader = gl.createShader(gl.VERTEX_SHADER);
    var fragmentShader = gl.createShader(gl.FRAGMENT_SHADER);

    gl.shaderSource(vertexShader,vertexShaderText);
    gl.shaderSource(fragmentShader, fragmentShaderText);

    gl.compileShader(vertexShader);
    gl.compileShader(fragmentShader);
    
    var program = gl.createProgram();
    gl.attachShader(program, vertexShader);
    gl.attachShader(program,fragmentShader);
    gl.linkProgram(program);

    gl.validateProgram(program);

    //create buffer
    var cubeVertices = 
    [ // X, Y, Z           R, G, B
        // Top
        -1.0, 1.0, -1.0,   0.5, 0.5, 0.5,
        -1.0, 1.0, 1.0,    0.5, 0.5, 0.5,
        1.0, 1.0, 1.0,     0.5, 0.5, 0.5,
        1.0, 1.0, -1.0,    0.5, 0.5, 0.5,

        // Left
        -1.0, 1.0, 1.0,    0.75, 0.25, 0.5,
        -1.0, -1.0, 1.0,   0.75, 0.25, 0.5,
        -1.0, -1.0, -1.0,  0.75, 0.25, 0.5,
        -1.0, 1.0, -1.0,   0.75, 0.25, 0.5,

        // Right
        1.0, 1.0, 1.0,    0.25, 0.25, 0.75,
        1.0, -1.0, 1.0,   0.25, 0.25, 0.75,
        1.0, -1.0, -1.0,  0.25, 0.25, 0.75,
        1.0, 1.0, -1.0,   0.25, 0.25, 0.75,

        // Front
        1.0, 1.0, 1.0,    1.0, 0.0, 0.15,
        1.0, -1.0, 1.0,    1.0, 0.0, 0.15,
        -1.0, -1.0, 1.0,    1.0, 0.0, 0.15,
        -1.0, 1.0, 1.0,    1.0, 0.0, 0.15,

        // Back
        1.0, 1.0, -1.0,    0.0, 1.0, 0.15,
        1.0, -1.0, -1.0,    0.0, 1.0, 0.15,
        -1.0, -1.0, -1.0,    0.0, 1.0, 0.15,
        -1.0, 1.0, -1.0,    0.0, 1.0, 0.15,

        // Bottom
        -1.0, -1.0, -1.0,   0.5, 0.5, 1.0,
        -1.0, -1.0, 1.0,    0.5, 0.5, 1.0,
        1.0, -1.0, 1.0,     0.5, 0.5, 1.0,
        1.0, -1.0, -1.0,    0.5, 0.5, 1.0,
    ];

    var cubeIndex = [
        0,1,2,
        0,2,3,

        5,4,6,
        6,4,7,

        8,9,10,
        8,10,11,

        13,12,14,
        15,14,12,

        16,17,18,
        16,18,19,

        22,20,23,
        21,20,22
    ];

    var cubeVertexBufferObject = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexBufferObject);
    gl.bufferData(gl.ARRAY_BUFFER,new Float32Array(cubeVertices) ,gl.STATIC_DRAW);

    var cubeIndexBufferObject = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cubeIndex);
    gl.bufferData(gl.ARRAY_BUFFER,new Uint16Array(cubeVertices) ,gl.STATIC_DRAW);

    var positionAttrLoc =  gl.getAttribLocation(program, 'vertPosition');
    var colorAttrLoc =  gl.getAttribLocation(program, 'vertColor');

    gl.vertexAttribPointer(
        positionAttrLoc,
        3,
        gl.FLOAT,
        gl.FALSE,
        6*Float32Array.BYTES_PER_ELEMENT,
        0
    );

    gl.vertexAttribPointer(
        colorAttrLoc,
        3,
        gl.FLOAT,
        gl.FALSE,
        6*Float32Array.BYTES_PER_ELEMENT,
        3* Float32Array.BYTES_PER_ELEMENT
    );

    gl.enableVertexAttribArray(positionAttrLoc);
    gl.enableVertexAttribArray(colorAttrLoc);

    gl.useProgram(program);

    gl.drawArrays(gl.TRIANGLES, 0, 3);
}