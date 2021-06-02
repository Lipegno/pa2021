precision mediump float;

attribute vec3 vertPosition;
attribute vec2 vertTexCoord;
varying vec2 fragTextCoord;

uniform mat4 _world;
uniform mat4 _view;
uniform mat4 _proj;
uniform mat4 _trans;

vec4 _position;

void main(){
   _position =  _view * _world * vec4(vertPosition,1.0);
   gl_Position = _proj * _trans * _position;
   fragTextCoord = vertTexCoord;
}