package com.mysite.sbbb.Files.dao;

import com.mysite.sbbb.Files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Integer> {
}
