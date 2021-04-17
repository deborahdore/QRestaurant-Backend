package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.utility.CSVUtility;
import com.certimetergroup.qrestaurant.utility.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class ExportService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${path.to.directory.export.files}")
    private String directoryPath;


    @Autowired
    private SocietyBaseService societyBaseService;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Path.of(directoryPath));
        } catch (IOException e) {
            throw new FailureException(ResponseType.EXPORT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreDestroy
    public void destroy() {
        deleteFiles("ALL");
        File file = new File(directoryPath);
        if (file.exists())
            file.delete();
    }

    public FileSystemResource exportCSVForClientInfected(List<Client> clients, Integer idSociety, Integer idNotification, Timestamp arrivalTime) {
        deleteFiles("1DAY");
        init();
        String[] headers = {"name", "street", "city", "country", "VAT num"};
        Society society = societyBaseService.getSociety(idSociety);
        String fileName = this.createTitle(idNotification, society.getSocietyName(), arrivalTime);
        File file = CSVUtility.createCSVClientInfected(Path.of(directoryPath), society, fileName, headers);
        headers = new String[]{"name", "surname", "phone"};
        return CSVUtility.populateCSVClientInfected(file, headers, clients);
    }

    public void deleteFiles(String MODE) {
        try {
            for (String file : Objects.requireNonNull((Path.of(directoryPath)).toFile().list())) {
                File f = new File(directoryPath + file);
                if (!f.isFile())
                    break;
                BasicFileAttributes attr = Files.readAttributes(Path.of(f.getPath()), BasicFileAttributes.class);
                logger.info("DELETING FILES...");
                switch (MODE) {
                    case "ALL":
                        Files.delete(Path.of(directoryPath + file));
                        break;
                    case "1DAY":
                        if (DateUtility.isBefore1DaysAgo(attr.creationTime())) {
                            Files.delete(Path.of(directoryPath + file));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* format of the title -> idNotification_societyName_arrivalTime_localTime*/
    public String createTitle(Integer idNotification, String societyName, Timestamp arrivalTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ITALIAN);
        return idNotification +
                "_" +
                societyName.replace(" ", "") +
                "_" +
                formatter.format(arrivalTime.toLocalDateTime().toLocalDate()) +
                "_" +
                LocalDate.now().format(formatter);
    }

}