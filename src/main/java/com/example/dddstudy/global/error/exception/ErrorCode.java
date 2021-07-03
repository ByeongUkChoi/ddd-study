package com.example.dddstudy.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNKNOWN_ERROR(1000, "UNKNOWN ERROR"),
    MISSING_REQUIRED_PARAMETERS(1001, "필수 값이 누락되었습니다."),

    // -- 주문
    NOT_FOUND_MENU(2001, "주문 메뉴을 찾을 수 없습니다."),
    CANNOT_ORDERED_MENU(2002, "주문 불가능한 상품입니다."),
    INVALID_MENU_PRICE(2003, "주문 메뉴 금액 오류입니다."),
    NOT_FOUND_OPTION_MENU_GROUP(2004, "옵션 그룹을 찾을 수 없습니다."),
    NOT_FOUND_OPTION_MENU_ITEM(2005, "옵션 상품을 찾을 수 없습니다."),
    INVALID_OPTION_MENU_ITEM_PRICE(2006, "주문 옵션 상품의 금액 오류입니다."),

    NOT_FOUND_ORDER(2007, "주문을 찾을 수 없습니다."),
    INVALID_ORDERER(2008, "주문자가 일치하지 않습니다."),
    ORDER_CANNOT_BE_CANCELED(2009, "주문 취소가 불가능 합니다."),

    INVALID_ORDER_STATUS_TO_PAY(2010, "주문 결제 시 상태 오류 입니다."),
    INVALID_ORDER_STATUS_TO_START_DELIVERY(2011, "배달 시작 시 상태 오류 입니다."),
    INVALID_ORDER_STATUS_TO_DELIVERED(2012, "배달 완료 시 상태 오류 입니다."),

    STORE_IS_NOT_OPEN(2013, "가게는 주문 불가능한 상태입니다."),
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
