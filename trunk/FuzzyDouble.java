import java.lang.Math;

public class FuzzyDouble{
	protected Double center;
	protected Double radius;

	FuzzyDouble(Double center, Double radius){
		this.center = center;
		this.radius = radius;
	}
	FuzzyDouble(){
		this.center = 0.0;
		this.radius = 0.0;
	}

	// renvoie la valeur centrale
	public Double getCenter(){
		return center;    
	}
	// radius correspond a + ou - X
	public Double getRadius(){
		return radius;    
	}
	// renvoie la plus haute valeur possible dont la probabilité est 'confidence'
	public Double getLowerBound(Double confidence){
		return confidence*radius + (center - radius);
	}
	// renvoie la plus basse valeur possible dont la probabilité est 'confidence'
	public Double getUpperBound(Double confidence){
		return center + radius - confidence*radius; 
	}
	// renvoie une valeur entre 0 et 1
	public Double getConfidenceAt(Double value){
		if( Math.abs(value - center) > radius ){
		return 0.0;
		}else{
			if(value < center){
				return (value - (center-radius) ) / radius;
			}else {
				return (center + radius - value) / radius;
			}
		}
	}
	
	public static FuzzyDouble add(FuzzyDouble a, FuzzyDouble b){
		return new FuzzyDouble(a.getCenter()+b.getCenter(),a.getRadius()+b.getRadius());
	}
	public static FuzzyDouble multily(FuzzyDouble a, FuzzyDouble b){
		return new FuzzyDouble(a.getCenter()*b.getCenter(),a.getRadius()*b.getRadius());
	}

	public static void unitTest(){
		FuzzyDouble fz1 = new FuzzyDouble();
		if(fz1.getCenter() != 0.0) System.out.println("error, default fuzzyDouble center should be 0");
		if(fz1.getRadius() != 0.0) System.out.println("error, default fuzzyDouble radius should be 0");
		FuzzyDouble fz2 = new FuzzyDouble(10.0,5.0);
		if(fz2.getCenter() != 10.0) System.out.println("error, the fuzzyDouble center should be 10");
		if(fz2.getRadius() != 5.0 ) System.out.println("error, the fuzzyDouble radius should be 5");
		if(fz2.getLowerBound(0.5) != 7.5) System.out.println("error, the lower bound should be 7.5");
		System.out.println(fz2.getLowerBound(0.5));
		if(fz2.getUpperBound(0.5) != 12.5) System.out.println("error, the lower bound should be 7.5");
		System.out.println(fz2.getLowerBound(0.5));
		if(fz2.getLowerBound(0.4) != 7.0) System.out.println("error, the lower bound should be 7.5");
		System.out.println(fz2.getLowerBound(0.4));
		if(fz2.getUpperBound(0.4) != 13.0) System.out.println("error, the lower bound should be 7.5");
		System.out.println(fz2.getLowerBound(0.4));
		if(fz2.getConfidenceAt(2.0) != 0.0) System.out.println("error, the confidence at 2 should be 0");
		if(fz2.getConfidenceAt(10.0) != 1.0) System.out.println("error, the confidence at 10 should be 1");
		if(fz2.getConfidenceAt(7.0) != 0.4) System.out.println("error, the confidence at 2 should be 0");
		if(fz2.getConfidenceAt(13.0) != 0.4) System.out.println("error, the confidence at 2 should be 0");
	}

	//public static void main(String[] args) { FuzzyDouble.unitTest(); }
	
}
