//Definimos el canvas
class Game{
	
	font = "Arial"
	
	intervalFunc = null;
	interval = null;
	
	constructor(){
		this.canvas = document.createElement("canvas");
		document.body.appendChild(this.canvas);
		this.canvas.height = $(document).height() * 0.97;
		this.canvas.width = $(document).width() * 0.97;
		
		//prepare attributes
		this.context = null;
		this.background = null;
		this.elements = [];
	}
	
	getContext(mode){
		this.context = this.canvas.getContext(mode);
	}
	
	setBackground(src){
		this.background = new Sprite(src, this);
	}
	
	drawBackground(x1, y1, x2, y2){
		if(this.background.ready){
			this.context.drawImage(this.background, x1, y1, x2, y2);
		}
	}
	
	draw(value){
		if(value.ready){
			this.context.drawImage(value, value.X, value.Y, value.size[0], value.size[1]);
		}
	}
	
	clearCanvas(){
		this.context.clearRect(0,0, this.canvas.width, this.canvas.height);
	}
	
	addElement(e){
		this.elements.push(e);
	}
		
	text(string, x, y, params){
		if(this.context != null){
			
			if("color" in params){
				this.context.fillStyle = params["color"];
			}
			
			if("size" in params){
				this.context.font = params["size"] + " " + this.font;
			}
			
			if("textAlign" in params){
				this.context.textAlign = params["textAlign"];
			}
			
			this.context.fillText(string, x, y);
		}
	}
	
	setInterval(func, time){
		this.interval = setInterval(func, time);
		this.intervalFunc = func;
	}
	
	gameOver(){
		
	}
}

class Space extends Game{
	
	alienSprite = "/images/alien.png";
	heartSprite = "/images/life.png";
	alienBulletSprite = "/images/alienBullet.png";
	
	TopMargin = 50;
	alienMargin = 10;
	alienTopMargin = this.TopMargin + this.alienMargin;
	
	numAliens = 0;
	alienSize = [75, 50]
	heartSize = [30, 30]
	alienBulletSize = [10, 20]
	
	bulletSpeed = 2;
	alienBulletSpeed = 10;
	
	bullets = []
	aliens = []
	aliensBullets = []
	alienROF = 50;
	
	spaceship = null;
		
	lifes = 3;
	hearts = []
	
	constructor(){
		super();
	}
	
	addSpaceship(ship){
		this.spaceship = ship;
	}
	
	moveBullets(){
		
		var markDelete = [];
		
		for (i = 0; i < this.bullets.length; i++) {
			this.bullets[i].Y -= this.bullets[i].size[1];
			
			var j = 0;
			for(j = 0; j < this.aliens.length; j++){
				if(this.bullets[i].Y <= this.aliens[j].Y + this.alienSize[1] && ( 
					this.bullets[i].X >= this.aliens[j].X &&
					this.bullets[i].X + this.bullets[i].size[0] <= this.aliens[j].X + this.aliens[j].size[0])){
						
					markDelete.push([this.aliens, j]);
					markDelete.push([this.bullets, i]);
				}
				continue;
			}
			
			if(this.bullets[i].Y <= 0){
				markDelete.push([this.bullets, i]); 
			}
		}

		for (i = 0; i < markDelete.length; i++) {
			markDelete[i][0].splice(markDelete[i][1], 1);
		}
	}
	
	createAliens(){
		
		var width = this.canvas.width - this.alienMargin;
		this.numAliens = Math.floor(width / (this.alienSize[0] + this.alienMargin));
		var extraMargin = width % (this.alienSize[0] + this.alienMargin);
		
		var i = 0;
		for(i = 0; i < this.numAliens; i++){
			var alien = new Sprite(this.alienSprite, this);
			
			alien.setSize(this.alienSize);
			alien.X = i * (this.alienSize[0] + this.alienMargin) + this.alienMargin + extraMargin / 2;
			alien.Y = this.alienTopMargin;
			
			this.aliens.push(alien);
		}
		
		for(i = 0; i < this.numAliens; i++){
			var alien = new Sprite(this.alienSprite, this);
			
			alien.setSize(this.alienSize);
			alien.X = i * (this.alienSize[0] + this.alienMargin) + this.alienMargin + extraMargin / 2;
			alien.Y = this.alienTopMargin + this.alienSize[1] + this.alienMargin;
			
			this.aliens.push(alien);
		}
	}
	
	moveAlienBullet(){
		
		var markDelete = [];
		
		var i = 0;
		for(i = 0; i < this.aliensBullets.length; i++){
			this.aliensBullets[i].Y += this.alienBulletSize[1];
			if(this.aliensBullets[i].Y >= this.spaceship.Y - this.alienBulletSize[1] &&
				this.aliensBullets[i].X + this.alienBulletSize[0] <= this.spaceship.X + this.spaceship.size[0] &&
				this.aliensBullets[i].X >= this.spaceship.X &&
				this.aliensBullets[i].Y <= this.spaceship.Y + this.spaceship.size[1]){
				markDelete.push([this.aliensBullets, i]);
				this.loseLife();
				break;
			}
			
			if(this.aliensBullets[i].Y >= this.canvas.height){
				markDelete.push([this.aliensBullets, i]); 
			}
		}
		
		i = 0;
		for (i = 0; i < markDelete.length; i++) {
			markDelete[i][0].splice(markDelete[i][1], 1);
		}
	}
	
	alienShoot(){
			
		var width = this.canvas.width - this.alienMargin;
		
		var aliensAlive = this.aliens.length;

		var numDisparos = 5 + aliensAlive / 5;
		var extraMargin = width % (this.alienSize[0] + this.alienMargin);
		
		var i = 0;
		for(i = 0; i < aliensAlive && numDisparos > 0; i++){
			var shoot = Math.floor((Math.random() * (aliensAlive / 2 + 2)));
			
			if(shoot == 0){
				var bullet = new Sprite((this.alienBulletSprite), this);
				bullet.setSize(this.alienBulletSize);
				bullet.X = this.aliens[i].X + this.alienSize[0] / 2 - this.alienBulletSize[0] / 2;
				bullet.Y = this.aliens[i].Y + this.alienSize[1];
				this.aliensBullets.push((bullet), this);
				
				numDisparos -= 1;
			}
		}
		
	}
	
	createLifes(){
		var i = 0;
		for(i = 0; i < this.lifes; i++){
			var heart = new Sprite(this.heartSprite, this);
			
			heart.setSize(this.heartSize);
			heart.X = 120 + i*(heart.size[0] + 10);
			heart.Y = 10;
			
			this.hearts.push(heart);
		}
	}
	
	drawLifes() {
		var i = 0;
		for(i = 0; i < this.lifes; i++){
			this.draw(this.hearts[i], this.hearts[i].X, this.hearts[i].Y, this.hearts[i].size[0], this.hearts[i].size[1]);
		}
	}
	
	loseLife(){
		this.lifes -= 1;
		
		if(this.lifes == 0){
			this.gameOver();
		}
		
		this.bullets = [];
		this.aliensBullets = [];
		
		this.spaceship.X = 0;
		this.spaceship.Y = this.canvas.height - this.spaceship.size[1];
	}
	
	gameOver(){
		clearInterval(this.interval);
		this.intervalFunc();
		this.text("GAME OVER", this.canvas.width / 2, this.canvas.height / 2, {"color" : "white", "textAlign" : "center", "size" : "100px"});
	}
}

class Sprite extends Image{

	constructor(src, space){
		super();
		this.ready = false;
		
		super.onload = this.onload();
		this.src = src;
		
		this.size = [100, 100]
		this.X = 0;
		this.Y = 0;
		
		this.Space = space;
	}
	
	onload(){
		this.ready = true;
	}
	
	setSizeX(x){
		this.size[0] = x;
	}
	
	setSizeY(y){
		this.size[1] = y;
	}
	
	setSize(s){
		this.size = s;
	}
}

// Nave espacial
class Spaceship extends Sprite{
	
	bulletSprite = "/images/bullet.png";
	bulletSize = [10, 20];
	
	X = 0;
	Y = 0;
	
	size = [100, 100];
	
	movSpeed = 5;
	
	rof = 20;
	
	constructor(space){
		super("/images/spaceship.png", space);
		
		this.Y = this.Space.canvas.height - this.size[1];
		
		space.addSpaceship(this);
		this.limitHeight = this.Space.canvas.height - 3 * this.size[1] > this.Space.TopMargin ? this.Space.canvas.height - 3 * this.size[1] : this.Space.TopMargin;
		console.log(this.limitHeight);
	}
	
	moveRight(){
		this.X = this.X + this.movSpeed;
		if(this.X > this.Space.canvas.width - this.size[0]){
			this.X = this.Space.canvas.width - this.size[0];
		}
	}
	
	moveLeft(){
		this.X = this.X - this.movSpeed;
		if(this.X < 0){
			this.X = 0;
		}
	}
	
	moveDown(){
		this.Y = this.Y + this.movSpeed;
		if(this.Y > this.Space.canvas.height - this.size[1]){
			this.Y = this.Space.canvas.height - this.size[1];
		}
	}
	
	moveUp(){
		this.Y = this.Y - this.movSpeed;
		if(this.Y <= this.limitHeight){
			this.Y = this.limitHeight;
		}
	}
	
	shoot(){
		var bullet = new Sprite(this.bulletSprite, this.Space);
		bullet.setSize(this.bulletSize);
		bullet.X = this.X + (this.size[0] / 2) - bullet.size[0] / 2;
		bullet.Y = this.Y - bullet.size[1];
		
		this.Space.bullets.push(bullet);
	}
}

/// CODE ------------------------------------------------------------------------------------------------------------
var lienzo = new Space();
lienzo.getContext("2d");
lienzo.setBackground("/images/starsbackground.jpg");
lienzo.createLifes();

var Ship = new Spaceship(lienzo);

var events = {}

lienzo.createAliens();

var render = function(){
	lienzo.clearCanvas();
	lienzo.drawBackground(0, 0, lienzo.canvas.width, lienzo.canvas.height);
	
	lienzo.text("LIFES: ", 10, 40, {"color" : "white", "size" : "30px"});
	
	lienzo.drawLifes();
	
	
	lienzo.draw(lienzo.spaceship);

	
	for (i = 0; i < lienzo.bullets.length; i++) {
		lienzo.draw(lienzo.bullets[i]);
	}
	
	for (i = 0; i < lienzo.aliensBullets.length; i++) {
		lienzo.draw(lienzo.aliensBullets[i]);
	}
	
	for (i = 0; i < lienzo.aliens.length; i++) {
		lienzo.draw(lienzo.aliens[i]);
	}
	
	if(lienzo.aliens.length == 0){
		lienzo.createAliens();
	}
}

//Animation frames
var maxframes = 10000;
var frame = 0;
var pendingShoot = false;

var loop = function(){
	render();
	
	if(frame % lienzo.bulletSpeed == 0){
		lienzo.moveBullets();
	}
	
	if(frame % lienzo.alienBulletSpeed == 0){
		lienzo.moveAlienBullet();
	}
	
	if((" " in events || pendingShoot) && (frame % Ship.rof == 0)){
		Ship.shoot();
		pendingShoot = false;
	}
	
	if(frame % lienzo.alienROF == 0){
		lienzo.alienShoot();
	}
	
	if("a" in events){
		Ship.moveLeft();
	}
	
	if("w" in events){
		Ship.moveUp();
	}
	
	if("s" in events){
		Ship.moveDown();
	}
	
	if("d" in events){
		Ship.moveRight();
	}
	frame += 1;
}

//Eventos
document.onkeydown = function(evt){
	const key = evt.key;
	
	if(key == " "){
		pendingShoot = true;
	}
	
	events[key] = true;
}

document.onkeyup = function(evt){
	const key = evt.key;
	
	delete events[key];
}

lienzo.setInterval(loop, 10);
//loop();
