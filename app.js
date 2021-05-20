// content for our vertex shader program
// attributes are "global" to be bound from our JS code
// varying are "globals" to be sent to the fragment shader
const vertexShaderText = `

    precision mediump float;

    attribute vec2 verticePosition;
    attribute vec3 verticeColor;

    varying vec3 fragColor;

    void main(){
        gl_Position = vec4(verticePosition,0.0,1.0);
        fragColor = verticeColor;
    }
`;

const fragmentShaderText = `
    precision mediump float;
    varying vec3 fragColor;

    void main(){
        gl_FragColor = vec4(fragColor,1);
    }
`;

function initWebGL(){
    console.log("Loaded!!");

    var canvas = document.getElementById("test_surface");//the canvas to be used by WebGL
    var gl     = canvas.getContext('webgl');//GL 'object' that will be used throughout our code

    if(!gl){
        console.log("Web GL not suported");
        gl = canvas.getContext('experimental-webgl');
    }

    if(!gl){
        alert("Browser is not compatible with WEB GL");
    }else{
        console.log("WEBGL context loaded!!")
    }

    gl.clearColor(0.8, 0.8, 0.8, 1);
    gl.clear(gl.COLOR_BUFFER_BIT|gl.DEPTH_BUFFER_BIT);

    var vertex_shader = gl.createShader(gl.VERTEX_SHADER); //creates the shader object
    var fragment_shader = gl.createShader(gl.FRAGMENT_SHADER);

    gl.shaderSource(vertex_shader, vertexShaderText);//sets the shader object source
    gl.shaderSource(fragment_shader,fragmentShaderText);

    gl.compileShader(vertex_shader); //compiles the defined source for the shader object
    gl.compileShader(fragment_shader);

    var program = gl.createProgram();
    gl.attachShader(program,vertex_shader);
    gl.attachShader(program,fragment_shader);

    gl.linkProgram(program);

    gl.validateProgram(program);

    var triangleVertices = [  // right here our vertices only exist in the
        //x, y,      r, g, b
        0.0, -0.5,   1.0,0.0,0.0,         // CPU
        -0.5, 0.5,   0.0,1.0,0.0,
        0.5,0.5,      0.0,0.0,1.0

    ];

    var verticesBuffer = gl.createBuffer(); // creates the buffer
    gl.bindBuffer(gl.ARRAY_BUFFER, verticesBuffer); // binds the buffer to the GL context
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(triangleVertices), gl.STATIC_DRAW); // set up the buffer to receive our vertices

    var verticeAttributeLocation = gl.getAttribLocation(program,'verticePosition');
    var colorAttributeLocation = gl.getAttribLocation(program,'verticeColor');


    gl.vertexAttribPointer(
        verticeAttributeLocation, // location defined above
        2,  // 2 points per vertice
        gl.FLOAT, // type of data, float by default
        gl.FALSE, // dont worry about normalization so far
        5*Float32Array.BYTES_PER_ELEMENT,  //the size in bytes of the buffer 2(xy)+3(rgb) = 5
        0); // this atribute starts at the 0 position

    gl.vertexAttribPointer(
        colorAttributeLocation, // location defined above
        3,  // 3 values (r,g,b) per color
        gl.FLOAT, // type of data, float by default
        gl.FALSE, // dont worry about normalization so far
        5*Float32Array.BYTES_PER_ELEMENT,  //the size in bytes of the buffer (2(xy)+3(rgb) = 5)
        2*Float32Array.BYTES_PER_ELEMENT); // this atribute starts at the 0 position        

    gl.enableVertexAttribArray(verticeAttributeLocation);  
    gl.enableVertexAttribArray(colorAttributeLocation);  

    gl.useProgram(program);

    //Main loop?
   /* var r=0;
    var g=0;
    var b=0;
    while(true){

    }*/

    gl.drawArrays(gl.TRIANGLES, 0, 3);

}