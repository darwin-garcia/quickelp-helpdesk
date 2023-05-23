var number

$(document).ready(function(){
	$('#send1').click(function (ev) {
		ev.preventDefault()
		productTable()
	})
	$('#send2').click(function (ev) {
		ev.preventDefault()
		secondPoint()
	})
	$('#send3').click(function (ev) {
		ev.preventDefault()
		thirdPoint()
	})
	$('#send4').click(function (ev) {
		ev.preventDefault()
		fourPoint()
	})
	$('#send5').click(function (ev) {
		ev.preventDefault()
		fifthPoint()
	})
	$('#send6').click(function (ev) {
		ev.preventDefault()
		sixthPoint()
	})
	$('#send7').click(function (ev) {
		ev.preventDefault()
		seventhPoint()
	})
	$('#send8').click(function (ev) {
		ev.preventDefault()
		eighthPoint()
	})
	$('#send9').click(function (ev) {
		ev.preventDefault()
		ninthPoint()
	})
	$('#send10').click(function (ev) {
		ev.preventDefault()
		tenthPoint()
	})
	$('#send11').click(function (ev) {
		ev.preventDefault()
		eleventhPoint()
	})
	$('#send12').click(function (ev) {
		ev.preventDefault()
		twelvePoint()
	})
	$('#send13').click(function (ev) {
		ev.preventDefault()
		thirteenPoint()
	})
	$('#send14').click(function (ev) {
		ev.preventDefault()
		fourteenPoint()
	})
	$('#send15').click(function (ev) {
		ev.preventDefault()
		fifteenPoint()
	})
	$('#send16').click(function (ev) {
		ev.preventDefault()
		sixteenPoint()
	})
	$('#send17').click(function (ev) {
		ev.preventDefault()
		seventeenPoint()
	})
})

function productTable () {
	number = $('#number').val()		
	$('#response').html('')
	for(let i=1; i<=number; i++) {
		var product = number*i
		$('#response').append('<label> '+number+'*'+i+'='+product+'</label><br>')		
	}
}
function secondPoint(){
	number = $('#number').val()	
	$('#response').html('')

	for(let i=1; i<=number; i++) {
		$('#response').append('<label>Ingrese un numero en el vector 1 [posicion ('+i+')]</label>')
		$('#response').append('<input type="number" class="form-control" id="numberA'+i+'">')		
	}
	for(let j=1; j<=number; j++) {
		$('#response').append('<label>Ingrese un numero en el vector 2 [posicion ('+j+')]</label>')
		$('#response').append('<input type="number" class="form-control" id="numberB'+j+'">')		
	}
	$('#response').append('<button class="btn btn-dark" id="second" onclick="SumaRestaVectores()">Calcular</button>')
}
function SumaRestaVectores(){
	let arrayNumbersA = []
	let arrayNumbersB = []
	let adition=0
	let result=0
	for(let i=1;i<=number;i++){
		let numi = parseInt($('#numberA'+i).val())
		arrayNumbersA.push(numi)
		adition=adition+numi
	}
	console.log(arrayNumbersA, arrayNumbersB)
	for(let j=1;j<=number;j++){
		let numj = parseInt($('#numberB'+i).val())
		arrayNumbersB.push(numj)
		result=result+numj
	}
	$('#response').append('<p class="text-font">La suma del vector 1 es: '+adition+'</label>')
	$('#response').append('<p class="text-font">La suma del vector 2 es: '+result+'</label>')
}
function thirdPoint(){
	number = $('#number').val()	
	$('#response').html('')
	for(let i=1; i<=number; i++)
		$('#response').append('<label> '+i+'</label><br>')
	$('#response').append('<label>Impares..........</label><br>')
	for(let i=1; i<=number; i++) {
		if(i%2!=0)
			$('#response').append('<label>Impares: '+i+'</label><br>')
	}
	$('#response').append('<label>Pares..........</label><br>')
	for(let i=1; i<=number; i++) {
		if(i%2==0)
			$('#response').append('<label>Pares: '+i+'</label><br>')
	}	
}	
function fourPoint(){
	number = $('#number').val()	
	let azar=0
	$('#response').html('')
	for(let i=1; i<=number; i++){
		azar=parseInt(Math.floor(Math.random()*number))
		$('#response').append('<label> '+azar+'</label><br>')
	}
}
function fifthPoint(){
	$('#response').html('')
	let total=0
	
	for(let i=1; i<=6; i++){
		$('#response').append('<label>Ingrese un numero en la posicion '+i+'</label>')
		$('#response').append('<input type="number" class="form-control" id="number'+i+'">')
	}
	$('#response').append('<button class="btn btn-dark" id="five" onclick="fifthCalcular()">Calcular</button>')
}
function fifthCalcular(){
	let number =[]
	let total=0
	let promedio=0
	let factorial=0
	for (let i=1; i <=6; i++) {
		let num = parseInt($('#number'+i).val())
		number.push(num)
		total = num + total
		factorial = factorial * num
	}
	promedio=total/6
	$('#response').append('<p><label>El promedio es: '+promedio+'</label></p>')
	$('#response').append('<p><label>El factorial es: '+factorial+'</label></p>')
}
function sixthPoint(){
	$('#response').html('')
	for(let i=100; i>0; i--)
		$('#response').append('<label> *'+i+'</label><br>')
}
function seventhPoint(){
	$('#response').html('')
	for(let i=0; i<=100; i++) {
		if(i%3==0)
		$('#response').append('<label> *'+i+'</label><br>')		
	}
}
function eighthPoint(){
	$('#response').html('')
	for(let i=0; i<=100; i++) {
		if(i%2==0)
		$('#response').append('<label> Multiplo de 2  :'+i+'</label><br>')
		if(i%3==0)
		$('#response').append('<label> Multiplo de 3 .:'+i+'</label><br>')		
	}
}
function ninthPoint(){
	number = $('#number').val()		
	$('#response').html('')
	for(let i=1 ; i<=number; i++) {
		let sumar = number + i
		$('#response').append('<label> '+number+'+'+i+'='+sumar+'</label><br>')		
	}
}
function tenthPoint(){
	number = $('#number').val()		
	$('#response').html('')
	for(let i=1; i<=number; i++) {
		var suma = number+i
		$('#response').append('<label> '+i+'</label><br>')		
	}
}
function eleventhPoint(){
	number = $('#number').val()		
	$('#response').html('')
	for(let i=1; i<=number; i++) {
		if(i%3==0)
		$('#response').append('<label> '+i+'</label><br>')		
	}
}
function twelvePoint(){
	number = $('#number').val()
	for(let x=1; x<=number; x++){
		if(number%x==0){
			$('#response').append('<p>'+x+' es Primo</p>')
		}
		else{		
			$('#response').append('')
		}
	}	
}
function thirteenPoint(){
	for(let i=1; i<=10; i++){
		$('#response').append('<label>Ingrese un numero en la posicion '+i+'</label>')
		$('#response').append('<input type="number" class="form-control" id="number'+i+'">')
	}
	$('#response').append('<button class="btn btn-dark" id="thirteen" onclick="thirteenCalcular()">Calcular</button>')
}
function thirteenCalcular(){
	let arrayNumbers = []
	let sumarPositivos=0
	let productoNegativos=0
	for(let i=1; i<=10;i++){
		let numi = parseInt($('#number'+i).val())
		arrayNumbers.push(numi)
		if(numi>0){
			sumarPositivos=sumarPositivos+numi
		}
		if(numi<0){
			productoNegativos=numi*productoNegativos
		}
	}
	$('#response').append('<p class="text-font">La suma de los numeros positivos es: '+sumarPositivos+'</label>')
	$('#response').append('<p class="text-font">El producto de los numeros negativos es: '+productoNegativos+'</label>')
}
function fourteenPoint(){
	num1 = $('#number1').val()	
	num2 = $('#number2').val()	
	$('#response').append('<p><label>Num1 = '+num2+'</label></p>')
	$('#response').append('<p><label>Num2 = '+num1+'</label></p>')
}
function fifteenPoint(){
	number = $('#number').val()		
	$('#response').html('')
	for(let i=1 ; i<=number; i++) {
		$('#response').append('<label>Ingrese el peso en kg para el empleado ('+i+')</label>')
		$('#response').append('<input type="number" class="form-control" id="number'+i+'">')
	}
	$('#response').append('<button class="btn btn-dark" id="fifteen" onclick="fifteenCalcular()">Calcular</button>')
}
function fifteenCalcular(){
	let arrayNumbers = []
	let mayoresKg=0
	let menoresKg=0
	for(let i=1;i<=number;i++){
		let numi = parseInt($('#number'+i).val())
		arrayNumbers.push(numi)
		if(numi<80){
			menoresKg=menoresKg+1
		}
		if(numi>80){
			mayoresKg=mayoresKg+1
		}		
	}
	console.log(arrayNumbers)
	$('#response').append('<p class="text-font">Empleados que pesan menos de 80 kg: '+menoresKg+'</label>')
	$('#response').append('<p class="text-font">Empleados que pesan mas de 80 kg:: '+mayoresKg+'</label>')
}
function sixteenPoint (){
	number = $('#number').val()
	vendor = $('#vendor').val()
	$('#response').html('')
	for(let i=1 ; i<=number; i++) {
		$('#response').append('<p><h4>Boleto Numero: '+i+'</h4></p>')
			$('#response').append('<p><strong>Codigo del Vendedor #: </strong></p>')
			$('#response').append('<select><option value="vendedor1" id="vendedor1">Vendedor 1</option> <option value="vendedor2" id="vendedor2">Vendedor 2</option> <option value="vendedor3" id="vendedor3">Vendedor 3</option></select>')
			$('#response').append('<p>Ingrese valor $: </p>')
			$('#response').append('<input type="number" class="form-control" id="number'+i+'">')
	}
	$('#response').append('<button class="btn btn-dark" id="sixteen" onclick="sixteenCalcular()">Calcular</button>')
}
function sixteenCalcular(){
	let arrayNumbers = []
	let comisionVendedor1=0
	let comisionVendedor2=0
	let comisionVendedor3=0
	let vend1 = $( "#vendedor1 option:selected" ).text();
	let vend2 = $( "#vendedor2 option:selected" ).text();
	let vend3 = $( "#vendedor3 option:selected" ).text();
	for(let i=1;i<=number;i++){
		let numi = parseInt($('#number'+i).val())
		arrayNumbers.push(numi)
		if(this.value=='vendedor1')
			comisionVendedor1=numi+comisionVendedor1
		if(this.value=='vendedor2')
			comisionVendedor2=numi+comisionVendedor2
		if(this.value=='vendedor3')
			comisionVendedor3=numi+comisionVendedor3
	}
	$('#response').append('<p class="text-font">La comision del vendedor 1 es: '+comisionVendedor1+'</label>')
	$('#response').append('<p class="text-font">La comision del vendedor 2 es: '+comisionVendedor2+'</label>')
	$('#response').append('<p class="text-font">La comision del vendedor 3 es: '+comisionVendedor3+'</label>')
}
function seventeenPoint(){
	num1 = $('#number1').val()	
	num2 = $('#number2').val()
	num3 = $('#number3').val()
	if(num1 == num2 && num2 == num3 && num3==num1){
		$('#response').append('<p class="text-font">Triangulo Equilatero</label>')
	}
	else{
		if(num1 == num2 || num2 == num3 || num3==num1)	
		$('#response').append('<p class="text-font">Triangulo Isoceles</label>')
		else{
			$('#response').append('<p class="text-font">Triangulo Escaleno</label>')
		}
	}		
}
