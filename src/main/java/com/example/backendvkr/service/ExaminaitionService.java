package com.example.backendvkr.service;

import com.example.backendvkr.model.Examination;
import com.example.backendvkr.model.Subject;
import com.example.backendvkr.model.Subscription;
import com.example.backendvkr.model.User;
import com.example.backendvkr.repository.ExaminationRepository;
import com.example.backendvkr.repository.SubjectRepository;
import com.example.backendvkr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class ExaminaitionService {
    private final UserRepository userRepository;
    private final ExaminationRepository examinationRepository;
    private final SubjectRepository subjectRepository;

    public ResponseEntity<?> uploadExaminaionFiles(MultipartFile excelFile, MultipartFile[] photos) {

//        return registrationService.login(loginDto);
    }

    public Integer[] accountInfo(Integer id) {
        User user = userRepository.getUserById(id);
        Integer counted = examinationRepository.countExaminationByUser_IdAndCreatedAt_Month(user.getId(), (short) LocalDate.now().getMonthValue());
        return new Integer[]{counted, user.getSubs().getChecks()};
    }

    public ResponseEntity<?> accountLastExamination(Integer id) {
//        return examinaitionService.accountInfo(id);
    }

    public void readAnswersFile(MultipartFile file) {
//читать только ту область, где ответы. ПО ячейкам
    }

    public void generateResponseFile() {
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

    public void getAnswerSchema(Integer subjectId) {
//        return examinaitionService.accountLastExamination(subjectId);
    }

    public void getResultByPhoto(MultipartFile[] photos) {
//        return examinaitionService.accountLastExamination(id);
    }

    public String[] subjectList(String type) {

        List<Subject> subjects=subjectRepository.getSubjectsBySubjType(type);
        return new String[]{};
    }
}

