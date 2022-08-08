package com.mysite.sbbb.files.dao;

import com.mysite.sbbb.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FilesRepository extends JpaRepository<Files, Long> {
}
