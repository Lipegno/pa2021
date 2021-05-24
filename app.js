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
        0.5,0.5,      1.0,0.0,1.0,


        -0.8,0.8,   1.0,0,0,  // new point
        -0.6,0.6,    0,1.0,0,
        -0.4,0.8,   0,0,1.0,

        0.8,-0.8,   1.0,0,0,  // new point
        0.6,-0.6,    0,1.0,0,
        0.4,-0.8,   0,0,1.0,

        0,0, 1.0,1.0,1.0,  // new point
        -0.2,-0.2, 1.0,1.0,1.0,
        0.2,-0.2, 1.0,1.0,1.0

    ];

    var triangleVerticesDuplicate  = Object.assign({},triangleVertices);
    /*[  // right here our vertices only exist in the
        //x, y,      r, g, b
        0.0, -0.5,   1.0,0.0,0.0,         // CPU
        -0.5, 0.5,   0.0,1.0,0.0,
        0.5,0.5,      1.0,0.0,1.0,


        -0.8,0.8,   0.0,1,0,  // new point
        -0.6,0.6,    0,1.0,0,
        -0.4,0.8,   1,0,0,

        0.8,-0.8,   0,1.0,0,  // new point
        0.6,-0.6,    0,1.0,0,
        0.4,-0.8,   0,1.0,0,

        0,0, 0.5,0.5,0.5,  // new point
        -0.2,-0.2, 0.5,0.5,0.5,
        0.2,-0.2, 0.5,0.5,0.5

    ];*/

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

    var s_x = 1;
    var s_y = 1;
    var s_r = 1;
    var s_g = 1;
    var s_b = 1;
    var i=0;
    function loop(){
       // console.log("teste");
        for(i=0;i<triangleVertices.length;i=i+5){
            triangleVertices[i]= triangleVertices[i]*s_x;     //x
            triangleVertices[i+1]=  triangleVertices[i+1]*s_y;   //y
            triangleVertices[i+2]=  triangleVertices[i+2]*s_r;    //r
            triangleVertices[i+3]= triangleVertices[i+3]*s_g;    //g
            triangleVertices[i+4]= triangleVertices[i+4]*s_b;   //b
            
        }
        s_x = s_x+0.001;
        s_y = s_y+0.001;
        s_r = s_r+0.001;
        s_g = s_g+0.001;
        s_b = s_b+0.001;
        if(s_x>1.1){
            s_x = 1;
            s_y = 1;
            s_r = 1;
            s_g = 1;
            s_b = 1;

            for(i=0;i<triangleVertices.length;i=i+5){
                triangleVertices[i]= triangleVerticesDuplicate[i];     //x
                triangleVertices[i+1]=  triangleVerticesDuplicate[i+1];
                triangleVertices[i+2]= triangleVerticesDuplicate[i+2];    //r
                triangleVertices[i+3]= triangleVerticesDuplicate[i+3];    //g
                triangleVertices[i+4]= triangleVerticesDuplicate[i+4];   //b
                
            }

            //console.log("aqui");
        }

        gl.clearColor(0.8, 0.8, 0.8, 1);
        gl.clear(gl.COLOR_BUFFER_BIT|gl.DEPTH_BUFFER_BIT);
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(triangleVertices), gl.STATIC_DRAW); // set up the buffer to receive our vertices
        gl.drawArrays(gl.TRIANGLES, 0, triangleVertices.length/5);

        requestAnimationFrame(loop);
    }
    requestAnimationFrame(loop);


}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }