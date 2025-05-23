package com.example.backendvkr.service;

import com.example.backendvkr.dto.MessageResponse;
import com.example.backendvkr.dto.TaskDto;
import com.example.backendvkr.model.*;
import com.example.backendvkr.repository.*;
import com.example.backendvkr.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExaminationService {
    private final UserRepository userRepository;
    private final ExaminationRepository examinationRepository;
    private final SubjectRepository subjectRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PhotoRepository photoRepository;
    private final PhotoService photoService;
    private final AuthorizService authorizService;
    private final AuthorizRepository authorizRepository;

    @Transactional
    public void uploadExaminationFiles(
            String token,
            MultipartFile excelFile,
            MultipartFile[] photos,
            Integer subjId) throws IOException {
        String email = jwtTokenUtil.getEmailFromAccessToken(token);
//        Authoriz authoriz=authorizRepository.findByEmail(email).orElseThrow(
//                () -> new UsernameNotFoundException("User Not Found with emil: " + email)
//        );
//        User user = userRepository.getUserByAuthoriz(authoriz);
        User user = userRepository.findByAuthoriz_Email(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with emil: " + email)
        );
        if (photos != null && photos.length > 0 && photos.length < 10) {
            Subject subject = subjectRepository.getSubjectsById(subjId);//предмет
            int questionsAmount = subject.getQuestions();
            List<TaskDto> answ = readAnswersFile(excelFile, questionsAmount);//читаем файл ответов

            Examination examination = new Examination(answ, subject, user);
            examination = examinationRepository.save(examination);
            examination.setPhotos(getPhotos(photos, examination));
        }
//        else
//            return ResponseEntity.badRequest().body(new MessageResponse("Что-ото пошло не так, повторите запрос позже."));
    }


    public Integer[] accountInfo(Integer id) {
        User user = userRepository.getUserById(id);
        Integer counted = examinationRepository.countExaminationByUser_IdAndCreatedAt_Month(user.getId(), (short) LocalDate.now().getMonthValue());
        return new Integer[]{counted, user.getSubs().getChecks()};
    }

    public ResponseEntity<?> accountLastExamination(Integer id) {
//        return examinaitionService.accountInfo(id);
        return ResponseEntity.ok("");
    }

    public List<TaskDto> readAnswersFile(MultipartFile file, int countQuestions) throws IOException {
//читать только ту область, где ответы. ПО ячейкам
        List<TaskDto> tasks = new ArrayList<>();
        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0); // Первый лист
            Row assignmentRow = sheet.getRow(0); // Строка "Задание"
            Row answerRow = sheet.getRow(1);     // Строка "Ответ"
            Row scoreRow = sheet.getRow(2);      // Строка "Балл"

            // Проходим по колонкам F-S (номера заданий 5-countQuestions)
            for (int i = 5; i <= countQuestions; i++) {
                TaskDto task = new TaskDto();
                task.setQuestion(getCellValue(assignmentRow.getCell(i)));
                task.setAnswer(getCellValue(answerRow.getCell(i)));
                task.setScore((int) scoreRow.getCell(i).getNumericCellValue());

                tasks.add(task);
            }
        }

        return tasks;
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

    public Map<Integer, String> subjectList() {
        List<Subject> subjects = subjectRepository.findAll(Sort.by("id").ascending());
        return subjects.stream()
                .collect(Collectors.toMap(
                        Subject::getId,
                        subject -> subject.getSubjType() + " " + subject.getName()
                ));
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private static Set<Photo> getPhotos(MultipartFile[] photos, Examination examination) throws IOException {
        Set<Photo> photosToRecognize = new LinkedHashSet<>();
        for (MultipartFile file : photos) {
            if (!file.isEmpty()) {
                Photo ph = new Photo(
                        file.getOriginalFilename(), (int) file.getSize(),
                        file.getBytes(), examination);
                photosToRecognize.add(ph);
            }
        }
        return photosToRecognize;
    }
}

