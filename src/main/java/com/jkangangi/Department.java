package com.jkangangi;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Department {

        @Id
        @SequenceGenerator(
                name = "department_id_sequence",
                sequenceName = "department_id_sequence",
                allocationSize = 20
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "department_id_sequence"
        )
        private Long id;
        private String name;
        private String department_head;


        public Department() {}

        public Department(String name, String department_head) {
                this.name = name;
                this.department_head = department_head;
        }

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

        public String getDepartment_head() {
                return department_head;
        }

        public void setDepartment_head(String department_head) {
                this.department_head = department_head;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Department that = (Department) o;
                return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(department_head, that.department_head);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, name, department_head);
        }

        @Override
        public String toString() {
                return "Department{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", department_head='" + department_head + '\'' +
                        '}';
        }
}
