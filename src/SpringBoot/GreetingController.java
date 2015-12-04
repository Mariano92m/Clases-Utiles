package hello;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private final AtomicLong counter = new AtomicLong();
	
	private LinkedList<GreetingAlumno> alumnos;

	//Sirve para llamar a todos los alumnos
	//Ej: localhost:8080/alumno
	@RequestMapping("/alumno")
    public LinkedList<GreetingAlumno> greetDef() {
		if(alumnos == null){
			LinkedList<GreetingAlumno> estudiante = new LinkedList<GreetingAlumno>();
			GreetingAlumno error = new GreetingAlumno(counter.incrementAndGet(),"error lista vacia");
			estudiante.add(error);
			return estudiante;
		}
		return alumnos;
	}
	
	
	//Sirve para llamar a un alumno en particular, segun su legajo
	//Ej: localhost:8080/alumno/20145
	@RequestMapping("/")
	public GreetingAlumno greetFull(@RequestParam(value = "nLeg") int numLeg){
		int pos;
		try{
			pos=search(numLeg);
			if(pos>0){
				return alumnos.get(pos);
			}
			if(pos == -1){
				throw new ExcepcionPropia("No se encontro el alumno");
			}
			throw new ExcepcionPropia("Lista vacia");
		} catch(ExcepcionPropia e){
			return new GreetingAlumno(counter.incrementAndGet(), e.getMessage());
		}
	}
	
	
	//Agrega un nuevo alumno
	//EJ: localhost:8080/add?nLeg=20145&name=Pedro&lastName=Romero&career=Electronica&cMat=19
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String greeting(@RequestParam(value = "nLeg")String nLeg, @RequestParam(value="name") String name,
						   @RequestParam(value = "lastName") String lastName, @RequestParam(value = "career") String career,
						   @RequestParam(value = "cMat") String cMat) throws ExcepcionPropia{
		try{
			int nL = Integer.parseInt(nLeg);
			if(search(nL) >=0){
				return "Legajo existente";
			} else {
				
				if(name.equals("") || lastName.equals("") || 
						career.equals("") || cMat.equals("")){
					throw new ExcepcionPropia("Es necesario completar todos los datos");
				}
				
				int mat = Integer.parseInt(cMat);
				GreetingAlumno al = new GreetingAlumno(counter.incrementAndGet(), nL, name, lastName, career, mat);
				addIt(al);
				return "Se agrego un nuevo alumno";
			}
		}catch(ExcepcionPropia e){
			return e.getMessage();
		}catch(NumberFormatException ex){
			return "No ingreso un NUMERO de Legajo";
		}
	}
	
	//Cambia un dato en especifico de un alumno segun su legajo
	//EJ: localhost:8080/20145?name=Fede
		@RequestMapping(value = "/{nLeg}", method = RequestMethod.PUT)
		public GreetingAlumno greeting(@PathVariable int nLeg, @RequestParam(value = "name", required = false) String name,
									   @RequestParam(value = "lastName", required = false) String lastName,
									   @RequestParam(value = "career", required = false) String career,
									   @RequestParam(value = "cMat", required = false) String cMat){
			try{
				int pos = search(nLeg);
				if(pos >= 0){
					if (name == null && lastName == null && career == null && cMat==null) {
						return new GreetingAlumno(counter.incrementAndGet(), "ERROR TODOS LOS PARAMETROS SON NULOS");
					}
					if (name != null) {// comprueba si hay parametro spring
						alumnos.get(pos).setName(name);// setea el nombre
					}
					if (lastName != null) {// comprueba si hay parametro spring
						alumnos.get(pos).setLastName(lastName);// setea el apellido
					}
					if (career != null) {// comprueba si hay parametro spring
						alumnos.get(pos).setCareer(career);// setea carrera
					}
					if (cMat !=null) {// comprueba si hay parametro spring
						int mat = Integer.parseInt(cMat);
						alumnos.get(pos).setCMat(mat);// setea cantidad de materias
					}
					
					return alumnos.get(pos);
				}
				//Devuelve -1 si no se encuentran alumnos.
				if(pos == -1){
					throw new ExcepcionPropia("No se encontro el alumno");
				}
				throw new ExcepcionPropia("La lista esta vacia");
			
			} catch(NumberFormatException e){
				return new GreetingAlumno(counter.incrementAndGet(), "Ingreso una palabra o una letra");
			} catch(ExcepcionPropia e){
				return new GreetingAlumno(counter.incrementAndGet(), e.getMessage());
			}
		}
	
	//Borra un alumno segun su legajo
	@RequestMapping(value="/del", method = RequestMethod.DELETE)
	public String greeting(@RequestParam(value = "numLeg") int nLeg){
		try{
			int pos = search(nLeg);
			if(pos >= 0){
				alumnos.remove(pos);
				return "Alumno Borrado";
			}
			if(pos==-1){
				throw new ExcepcionPropia("Alumno no encontrado");
			}
			throw new ExcepcionPropia("Lista vacia");
		} catch (ExcepcionPropia e) {
			return e.getMessage();
		} catch (NumberFormatException ex) {
			return "Se ingreso una letra o una palabra";
		}
	}
		
	//Busca los datos del alumno
	private int search(int nLeg) throws ExcepcionPropia{
		boolean found = false;
		int i=0;
		if(alumnos != null){
			int l = alumnos.size();
			while (!found && i<l){
				if(nLeg == alumnos.get(i).getNLeg()){
					found = true;
				}else{
					i++;
				}
			}
			
			if(found){
				return i;
			}
			return -1;
		}
		return -2;
	}
	//Agrega un nuevo alumno a la lista de alumnos.
	private GreetingAlumno addIt(GreetingAlumno alu){
		if(alumnos == null){
			alumnos = new LinkedList<GreetingAlumno>();
		}
		alumnos.add(alu);
		return alu;
	}
}
	
	

