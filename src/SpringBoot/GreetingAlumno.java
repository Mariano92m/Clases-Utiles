package hello;

public class GreetingAlumno{

    private final long id;
    
    private int nLeg;
    private String name;
    private String lastName;
    private String career;
    private int cMat;
    private String coment;

    
    //Constructor por Defecto
    public GreetingAlumno(long id, String coment) {
        this.id = id;
        this.coment = coment;
    }
    
    //Constructor Alumno
    public GreetingAlumno(long id, int nLeg, String name, String lastName, 
    		String career, int cMat){
    	this.id = id;
    	this.nLeg = nLeg;
    	this.name = name;
    	this.lastName = lastName;
    	this.career = career;
    	this.cMat = cMat;
    	this.coment = "200 OK";
    }
    
    //Getters y Setters
    public long getId() {
		return id;
	}

	public int getNLeg() {
		return nLeg;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCareer() {
		return career;
	}

	public int getCantMat() {
		return cMat;
	}
	
	public String getComent() {
		return coment;
	}

	public void setNLeg(int nLeg) {
		this.nLeg = nLeg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public void setCMat(int cMat) {
		this.cMat = cMat;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}
}
