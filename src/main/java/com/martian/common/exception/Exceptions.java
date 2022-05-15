package com.martian.common.exception;

import static com.martian.common.exception.Exceptions.Constant.*;

/**
 * 枚举异常常量
 */
public interface Exceptions {

    /**
     * 异常模块code
     */
    interface Constant {
        int SYSTEM = 900;// 系统异常
        int FILE = 950;// 文件
        int USER = 1000;// 用户
        int DEPART = 2000;// 院系
        int CLAZZ = 3000;// 班级
        int TEACHER = 4000;// 教师
        int STUDENT = 5000;// 学生
        int COURSE = 6000;// 课程
        int BOOK = 2000;// 图书
        int READER = 3000;// 读者
        int BORROW = 4000;// 借阅记录
    }

    enum System implements ExceptionType {
        SYSTEM_ERROR(SYSTEM + 1, "系统异常"),
        REQUEST_PARAM_ERROR(SYSTEM + 2, "请求参数错误"),
        REQUEST_METHOD_ERROR(SYSTEM + 3, "请求方式错误，请检查GET或者POST方式是否错误"),
        ADD_FAIL(SYSTEM + 4, "添加失败"),
        UPDATE_FAIL(SYSTEM + 5, "修改失败"),
        SAVE_FAIL(SYSTEM + 6, "保存失败"),
        DEL_FAIL(SYSTEM + 7, "删除失败"),
        SYSTEM_ADMIN_NOT_LOGIN(SYSTEM + 8, "您还未登录，请先登录"),;

        private int code;
        private String description;

        System(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum File implements ExceptionType {
        FILE_UPLOAD_NOT_EXIST(FILE + 1, "文件不存在，请重新上传"),
        FILE_UPLOAD_DIR_NOT_WRITE_PERMISSION(FILE + 2, "目录没有写权限，上传失败"),
        FILE_UPLOAD_TOO_BIG(FILE + 3, "文件过大，请重新上传"),
        FILE_UPLOAD_NOT_FIND_PATH(FILE + 4, "系统找不到指定的路径，上传失败"),
        FILE_UPLOAD_ERROR(FILE + 5, "文件上传错误"),;

        private int code;
        private String description;

        File(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Depart implements ExceptionType {
        DEPART_ID_NULL(DEPART + 1, "院系Id不能为空"),
        DEPART_NOT_EXIST(DEPART + 2, "院系不存在"),;

        private int code;
        private String description;

        Depart(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Clazz implements ExceptionType {
        CLAZZ_ID_NULL(CLAZZ + 1, "班级Id不能为空"),
        CLAZZ_NOT_EXIST(CLAZZ + 2, "班级不存在"),
        CLAZZ_NAME_CAN_NOT_NULL(CLAZZ + 3, "班级名称不能为空"),;

        private int code;
        private String description;

        Clazz(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Teacher implements ExceptionType {
        TEACHER_ID_NULL(TEACHER + 1, "教师Id不能为空"),
        TEACHER_NOT_EXIST(TEACHER + 2, "教师不存在"),
        TEACHER_NAME_CAN_NOT_NULL(TEACHER + 3, "教师名称不能为空"),;

        private int code;
        private String description;

        Teacher(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Student implements ExceptionType {
        STUDENT_ID_NULL(STUDENT + 1, "学生Id不能为空"),
        STUDENT_NOT_EXIST(STUDENT + 2, "学生不存在"),
        STUDENT_NAME_CAN_NOT_NULL(STUDENT + 3, "学生名称不能为空"),
        STUDENT_SCORE_NOT_EXIST(STUDENT + 4, "学生成绩不存在"),
        ;

        private int code;
        private String description;

        Student(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Course implements ExceptionType {
        COURSE_ID_NULL(COURSE + 1, "课程Id不能为空"),
        COURSE_NOT_EXIST(COURSE + 2, "课程不存在"),
        COURSE_NAME_CAN_NOT_NULL(COURSE + 3, "课程名称不能为空"),
        COURSE_YEAR_CAN_NOT_NULL(COURSE + 4, "年份不能为空"),
        COURSE_TERM_CAN_NOT_NULL(COURSE + 5, "学期不能为空"),
        COURSE_HAS_EXISTED(COURSE + 6, "课程已存在，请勿重复添加"),;

        private int code;
        private String description;

        Course(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Book implements ExceptionType {
        BOOK_ID_NULL(BOOK + 1, "图书Id不能为空"),
        BOOK_NOT_EXIST(BOOK + 2, "图书不存在"),;

        private int code;
        private String description;

        Book(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Reader implements ExceptionType {
        READER_ID_NULL(READER + 1, "读者Id不能为空"),
        READER_NOT_EXIST(READER + 2, "读者不存在"),
        READER_BORROW_NUM_TOO_BIG(READER + 3, "读者可借阅的图书数量超过了系统限制，请先归还图书"),;

        private int code;
        private String description;

        Reader(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum Borrow implements ExceptionType {
        BORROW_ID_NULL(BORROW + 1, "借阅Id不能为空"),
        BORROW_NOT_EXIST(BORROW + 2, "借阅信息不存在"),;
        private int code;
        private String description;

        Borrow(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    enum User implements ExceptionType {
        USERNAME_NULL(USER + 1, "请输入用户名"),
        PWD_NULL(USER + 2, "请输入密码"),
        USERNAME_PWD_ERROR(USER + 3, "用户名或密码错误"),
        USER_NOT_EXIST(USER + 4, "用户不存在"),
        USER_DISABLED(USER + 5, "用户被禁用，请联系管理员"),
        USER_ID_NULL(USER + 6, "用户Id不能为空"),
        USERNAME_HAS_USED(USER + 7, "用户名已被使用，请重新输入"),
        NEW_PWD_NOT_NULL(USER + 8, "密码不能为空"),
        RE_NEW_PWD_NOT_NULL(USER + 9, "确认密码不能为空"),
        TWO_PWD_NOT_EQUAL(USER + 10, "两次输入的密码不一致，请重新输入"),;

        private int code;
        private String description;

        User(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
