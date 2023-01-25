package ec.gob.iess.actividades.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "actividades")
@Getter
@Setter
public class Actividad { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fecha;
	
	private boolean completado;

}
