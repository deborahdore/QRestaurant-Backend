package com.certimetergroup.qrestaurant.utility;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.model.Society;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CSVUtility {

    private final static Logger logger = LoggerFactory.getLogger(CSVUtility.class);

    public static File createCSVClientInfected(Path directoryPath, Society society, String name, String[] headers) {
        logger.info("CREATING NEW CSV..");
        try {
            File file = new File(directoryPath + "/" + name + ".csv");

            if (file.exists())
                file.delete();
            if (!file.createNewFile())
                throw new FailureException(ResponseType.EXPORT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(file, true), CSVFormat.DEFAULT.withHeader(headers).withDelimiter(';'));
            csvPrinter.print(society.getSocietyName());
            csvPrinter.print(society.getStreet());
            csvPrinter.print(society.getCity());
            csvPrinter.print(society.getCountry());
            csvPrinter.print(society.getVatnum());
            csvPrinter.printRecord();
            csvPrinter.flush();
            csvPrinter.close();

            return file;
        } catch (IOException e) {
            throw new FailureException(ResponseType.EXPORT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static FileSystemResource populateCSVClientInfected(File file, String[] headers, List<Client> clients) {
        try {
            CSVPrinter printer = new CSVPrinter(new FileWriter(file, true), CSVFormat.DEFAULT.withHeader(headers).withDelimiter(';'));
            clients.forEach(client -> {
                try {
                    printer.print(client.getName());
                    printer.print(client.getSurname());
                    printer.print(client.getPhone());
                    printer.printRecord();

                } catch (IOException e) {
                    throw new FailureException(ResponseType.EXPORT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            });
            printer.flush();
            printer.close();
        } catch (IOException e) {
            throw new FailureException(ResponseType.EXPORT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new FileSystemResource(file);
    }
}
