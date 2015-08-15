package tr.lkd.lyk2015.springtodo.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table (name = "T_TODOS")
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id				//primary key
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String name;

	@Size(max = 10)
	@Column(name = "description")
	private String desc;

	@DateTimeFormat(iso = ISO.DATE)
	private Calendar dueDate;
	private boolean done;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getDate() {
		Date date = this.dueDate.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String datetime = null;
		datetime = format1.format(date);
		// System.out.println(datetime);

		return datetime;
	}
}
