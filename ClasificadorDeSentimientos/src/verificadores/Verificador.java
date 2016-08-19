package verificadores;

public class Verificador {
	public Boolean esSigno(char c){
		Boolean resultado=false;
		if((c >= '!' && c <= '/' ) || (c >= ':' && c <= '@' ) || (c >= '[' && c <= 96) || (c >= 123 && c <= 126)){
			resultado=true;
		}
		return resultado;
	}
	public Boolean esMay(char c){
		Boolean resultado=false;
		if(c >= 'A' && c <= 'Z'){
			resultado=true;
		}
		return resultado;
	}
}
