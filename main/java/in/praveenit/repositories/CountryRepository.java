package in.praveenit.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.praveenit.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

}
