function loadTextResource(location, callback){
    var request = new XMLHttpRequest();
    request.open('GET',location,true);
    request.onload= function(){
        console.log(request.status);
        //console.log("************"+request.responseText);
        callback(request.responseText);
    };
    request.send();
}

function loadImage(location , callback){
    var image = new Image();
    image.onload = function(){
        callback(image);
    }
    image.src = location;
}

function loadJSONResource(location, callback){
    loadTextResource(location, function(result){
        try{
            callback(JSON.parse(result));
        }catch(e){
            console.log("ERROR PARSING MODEL");
        }
    });
}