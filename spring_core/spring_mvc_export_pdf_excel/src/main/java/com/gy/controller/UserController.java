package com.gy.controller;

import com.gy.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 默认键名: userList
     * @return
     */
    @ModelAttribute(name = "users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user " + i);
            user.setPhone("1331234567" + i);
            users.add(user);
        }
        return users;
    }

    @RequestMapping(value = "/export/pdf",
            method = RequestMethod.GET)
    public String exportPdf(Model model) {

        return "usersAsPdf"; // ac.getBean("usersAsPdf")
    }

    @RequestMapping(value = "/export/execl",
            method = RequestMethod.GET)
    public String exportExcel(Model model) {
        return "usersAsExcel";
    }

    /**
     * 这种形式的导出为文件，不管你是什么格式都可以。只需要会是用指定格式的库。
     * @param response
     */
    @GetMapping("/users/summary")
    public void download(HttpServletResponse response) {
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=user_summary");

        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("sheet1");

            // Create a Font for styling header cells
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            // Create a CellStyle with the font
            CellStyle headerCellStyle = wb.createCellStyle();
            headerCellStyle.setFont(headerFont);


            String[] header = new String[]{"User Name", "Phone"};
            // write header
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(i);
            }

            //List<User> users = userService.getAll();
            List<User> users = new ArrayList<>();
            User user = new User();
            user.setUsername("藏三");
            user.setPhone("13434335");
            users.add(user);
            user = new User();
            user.setUsername("李四");
            user.setPhone("234353");
            users.add(user);
            for (int i = 0; i < users.size(); i++) {
                XSSFRow row = sheet.createRow(i + 1);
                setCell(row, 0, CellType.STRING, users.get(i).getUsername());
                setCell(row, 1, CellType.STRING, users.get(i).getPhone());
                //setCell(wb, row, 2, CellType.NUMERIC, userForm.getFracStartDate());
            }

            wb.write(response.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException("Cannot export XLSX file: " + ex.getMessage(), ex);
        }
    }

    private void setCell(XSSFRow row, int i, CellType type, String value) {
        XSSFCell cell = row.createCell(i);
        cell.setCellType(type);
        cell.setCellValue(value);
    }


}
