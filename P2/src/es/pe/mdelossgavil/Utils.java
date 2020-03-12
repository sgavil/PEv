package es.pe.mdelossgavil;

public class Utils {

	public static float float_between_range(float min,float max) {
		return (float)(min + Math.random() * (max - min));
	}
}
