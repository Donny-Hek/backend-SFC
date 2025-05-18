package com.example.backendvkr.service;

import com.example.backendvkr.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ExaminaitionService {
    public ResponseEntity<?> uploadExaminaionFiles(MultipartFile excelFile, MultipartFile[] photos) {
//        return registrationService.login(loginDto);
    }

    public Integer accountInfo(Integer id) {
//        return registrationService.login(loginDto);
    }

    public ResponseEntity<?> accountLastExamination (Integer id) {
//        return examinaitionService.accountInfo(id);
    }

    public void readAnswersFile(MultipartFile file){
//читать только ту область, где ответы. ПО ячейкам
    }

    public void generateResponseFile(){
//каждый раз после получения распознавания из фото,
// сохранять json строку для бланка с фото. Каждую строку добалять в файл с ответами.
//        byte[] addRowToExcel(MultipartFile file, List<String> newRowData) throws IOException {
//            // Открываем существующий файл
//            try (InputStream inputStream = file.getInputStream();
//                 Workbook workbook = WorkbookFactory.create(inputStream)) {
//
//                // Получаем первый лист
//                Sheet sheet = workbook.getSheetAt(0);
//
//                // Создаем новую строку в конце
//                int lastRowNum = sheet.getLastRowNum();
//                Row newRow = sheet.createRow(lastRowNum + 1);
//
//                // Заполняем ячейки
//                for (int i = 0; i < newRowData.size(); i++) {
//                    Cell cell = newRow.createCell(i);
//                    cell.setCellValue(newRowData.get(i));
//                }
//
//                // Автоподбор ширины столбцов
//                for (int i = 0; i < newRowData.size(); i++) {
//                    sheet.autoSizeColumn(i);
//                }
//
//                // Конвертируем в byte[]
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                workbook.write(outputStream);
//                return outputStream.toByteArray();
//            }

    }

    public void getAnswerSchema (Integer subjectId) {
//        return examinaitionService.accountLastExamination(id);
    }

    public void getResultByPhoto (MultipartFile[] photos) {
//        return examinaitionService.accountLastExamination(id);
    }
}
