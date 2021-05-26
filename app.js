const vertexShaderText = 
`
precision mediump float;

attribute vec2 vertPosition;
attribute vec3 vertColor;
varying vec3 fragColor;

void main(){
   gl_Position = vec4(vertPosition,0.0,1.0);
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
    var trianglevertices = [
        0.0,0.5,    1.0,0.0,0.0,
        -0.5,-0.5, 0.0,1.0,0.0,
        0.5,-0.5, 0.0,0.0,1.0
    ]; 

    var triangleVertexBufferObject = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexBufferObject);
    gl.bufferData(gl.ARRAY_BUFFER,new Float32Array(trianglevertices) ,gl.STATIC_DRAW);

    var positionAttrLoc =  gl.getAttribLocation(program, 'vertPosition');
    var colorAttrLoc =  gl.getAttribLocation(program, 'vertColor');

    gl.vertexAttribPointer(
        positionAttrLoc,
        2,
        gl.FLOAT,
        gl.FALSE,
        5*Float32Array.BYTES_PER_ELEMENT,
        0
    );

    gl.vertexAttribPointer(
        colorAttrLoc,
        3,
        gl.FLOAT,
        gl.FALSE,
        5*Float32Array.BYTES_PER_ELEMENT,
        2* Float32Array.BYTES_PER_ELEMENT
    );

    gl.enableVertexAttribArray(positionAttrLoc);
    gl.enableVertexAttribArray(colorAttrLoc);

    gl.useProgram(program);

    gl.drawArrays(gl.TRIANGLES, 0, 3);
}