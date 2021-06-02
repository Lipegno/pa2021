//https://open.gl/textures


var directions = new Float32Array(3);    


function loadResources(){
    console.log("Loading resources ....");
    loadTextResource("vertexShader.glsl",function(vertexShaderText){
        loadTextResource("fragmentShader.glsl",function(fragmentShaderText){
            loadJSONResource("novo.json", function(modelo){
                loadImage("ball.png",function(texture){
                    initAnimation(vertexShaderText, fragmentShaderText,modelo,texture);
                });
            });
        });
    })
}

function initAnimation(vertexShaderText,fragmentShaderText,model,tex){
    console.log("Loaded! !");
    var canvas =  document.getElementById("work_surface");
    var gl = canvas.getContext('webgl'); 

    var aspectRation =  canvas.width / canvas.height;

    if(!gl){
        gl =  canvas.getContext('experimental-webgl');
    }

    if(!gl){
        alert("browser does not support webGL");
    }

    gl.clearColor(0.75, 0.75, 0.75, 1);
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    gl.enable(gl.DEPTH_TEST);
    gl.enable(gl.CULL_FACE);
    
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

    gl.useProgram(program);

    var cubeVertices = 
	[ // X, Y, Z           U, V
		// Top
		-1.0, 1.0, -1.0,   0, 0,
		-1.0, 1.0, 1.0,    0, 1,
		1.0, 1.0, 1.0,     1, 1,
		1.0, 1.0, -1.0,    1, 0,

		// Left
		-1.0, 1.0, 1.0,    0, 0,
		-1.0, -1.0, 1.0,   1, 0,
		-1.0, -1.0, -1.0,  1, 1,
		-1.0, 1.0, -1.0,   0, 1,

		// Right
		1.0, 1.0, 1.0,    1, 1,
		1.0, -1.0, 1.0,   0, 1,
		1.0, -1.0, -1.0,  0, 0,
		1.0, 1.0, -1.0,   1, 0,

		// Front
		1.0, 1.0, 1.0,    1, 1,
		1.0, -1.0, 1.0,    1, 0,
		-1.0, -1.0, 1.0,    0, 0,
		-1.0, 1.0, 1.0,    0, 1,

		// Back
		1.0, 1.0, -1.0,    0, 0,
		1.0, -1.0, -1.0,    0, 1,
		-1.0, -1.0, -1.0,    1, 1,
		-1.0, 1.0, -1.0,    1, 0,

		// Bottom
		-1.0, -1.0, -1.0,   1, 1,
		-1.0, -1.0, 1.0,    1, 0,
		1.0, -1.0, 1.0,     0, 0,
		1.0, -1.0, -1.0,    0, 1,
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
    gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeIndexBufferObject);
    gl.bufferData(gl.ELEMENT_ARRAY_BUFFER,new Uint16Array(cubeIndex) ,gl.STATIC_DRAW);

    var positionAttrLoc =  gl.getAttribLocation(program, 'vertPosition');
    var texAttrLoc =  gl.getAttribLocation(program, 'vertTexCoord');
    var worldMatUniformLocation = gl.getUniformLocation(program, '_world');
    var projMatUniformLocation = gl.getUniformLocation(program, '_proj');
    var viewMatUniformLocation = gl.getUniformLocation(program, '_view');
    var transMatUniformLocation = gl.getUniformLocation(program, '_trans');

    var worldMatrix = new Float32Array(16); // 4x4
    var projMatrix = new Float32Array(16); // 4x4
    var viewMatrix = new Float32Array(16); // 4x4
    var transMatrix = new Float32Array(16); // 4x4


    glMatrix.mat4.identity(worldMatrix);
    glMatrix.mat4.identity(projMatrix);
    glMatrix.mat4.identity(viewMatrix);
    glMatrix.mat4.identity(transMatrix);


    glMatrix.mat4.lookAt(viewMatrix,[0,0,-5],[0,0,0],[0,1,0]);
    glMatrix.mat4.perspective(projMatrix,glMatrix.glMatrix.toRadian(60),aspectRation,0.1,1000.0);

    gl.uniformMatrix4fv(worldMatUniformLocation, gl.FALSE, worldMatrix);
    gl.uniformMatrix4fv(projMatUniformLocation, gl.FALSE, projMatrix);
    gl.uniformMatrix4fv(viewMatUniformLocation, gl.FALSE, viewMatrix);

    console.log(viewMatrix);
    console.log(projMatrix);

    gl.vertexAttribPointer(
        positionAttrLoc,
        3,
        gl.FLOAT,
        gl.FALSE,
        5*Float32Array.BYTES_PER_ELEMENT,
        0
    );

    gl.vertexAttribPointer(
        texAttrLoc,
        2,
        gl.FLOAT,
        gl.FALSE,
        5*Float32Array.BYTES_PER_ELEMENT,
        3* Float32Array.BYTES_PER_ELEMENT
    );

    var angle = 0;
    gl.enableVertexAttribArray(positionAttrLoc);
    gl.enableVertexAttribArray(texAttrLoc);
    var texture = gl.createTexture();
    gl.bindTexture(gl.TEXTURE_2D, texture);
    gl.texParameteri(gl.TEXTURE_2D,gl.TEXTURE_WRAP_S,gl.CLAMP_TO_EDGE); // dizer como é que a texture
    gl.texParameteri(gl.TEXTURE_2D,gl.TEXTURE_WRAP_T,gl.CLAMP_TO_EDGE); // será mapeada na geometria
    gl.texParameteri(gl.TEXTURE_2D,gl.TEXTURE_MIN_FILTER, gl.LINEAR);
    gl.texParameteri(gl.TEXTURE_2D,gl.TEXTURE_MAG_FILTER, gl.LINEAR); 
    gl.texImage2D(gl.TEXTURE_2D,0,gl.RGBA,gl.RGBA,gl.UNSIGNED_BYTE,document.getElementById("tex"));

    gl.useProgram(program);

    var x_rotation_matrix = new Float32Array(16);
    var y_rotation_matrix = new Float32Array(16);

    var indentityMatrix = new Float32Array(16);
    glMatrix.mat4.identity(indentityMatrix);

    var loop = function(){
        angle = angle+0.01;
        glMatrix.mat4.rotate(x_rotation_matrix,indentityMatrix,angle,[1,0,0]);
        glMatrix.mat4.rotate(y_rotation_matrix,indentityMatrix,angle,[0,1,0]);

        glMatrix.mat4.mul(worldMatrix,x_rotation_matrix,y_rotation_matrix);
        gl.uniformMatrix4fv(worldMatUniformLocation, gl.FALSE, worldMatrix);
        
        transMatrix[12] = directions[0];
        transMatrix[13] = directions[1];
        transMatrix[14] = directions[2];

        gl.uniformMatrix4fv(transMatUniformLocation, gl.FALSE, transMatrix);


        gl.clearColor(0.75, 0.75, 0.75, 1);
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        gl.drawElements(gl.TRIANGLES, cubeIndex.length,gl.UNSIGNED_SHORT,0);
        
        
        requestAnimationFrame(loop);
    }
    requestAnimationFrame(loop);

}

function handleTransClick(event){
    if(event.target.id=="left"){
        console.log("left");
        directions[0]=directions[0]-0.1;
    }
    if(event.target.id=="right"){
        console.log("right");
        directions[0]=directions[0]+0.1;
    }
    if(event.target.id=="up"){
        console.log("up");
        directions[1]=directions[1]+0.1;
    }
    if(event.target.id=="down"){
        console.log("down");
        directions[1]=directions[1]-0.1;
    }
    if(event.target.id=="in"){
        console.log("in");
        directions[2]=directions[2]+0.1;
    }
    if(event.target.id=="out"){
        console.log("out");
        directions[2]=directions[2]-0.1;
    }
}