/**
 * 
 */

class Commonjs {

	constructor()
	{
		
	}
	
	isEmpty(value) {
		if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){ 
			return true 
		}else{ 
			return false
		}
	}
	
	isNumber(value) {
		var patten = /[^0-9]/; //숫자만 허용
		if (!patten.test(value)) {
			return false
		} else {
			return true;
		}
		
	}
}