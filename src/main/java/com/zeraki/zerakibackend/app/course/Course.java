
package com.zeraki.zerakibackend.app.course;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zeraki.zerakibackend.app.institution.Institution;
import com.zeraki.zerakibackend.app.student.Student;

import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "courseId")
    private List<Student> studentList;
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    @ManyToOne
    private Institution institutionId;
    @Column(name = "keywords")
    private String keywords;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
