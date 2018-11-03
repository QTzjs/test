function getRootPath() {
    // 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPaht + projectName);
}
/**
 * 验证字符串长度
 */
function checkByteLength(str, minlen, maxlen) {
    if (str == null)
        return false;
    var l = str.length;
    var blen = 0;
    for (i = 0; i < l; i++) {
        if ((str.charCodeAt(i) & 0xff00) != 0) {
            blen++;
        }
        blen++;
    }
    if (blen > maxlen || blen < minlen) {
        return false;
    }
    return true;
}

/**
 * 用户名只能含有字母、数字和下划线，且长度在5到20位之内
 */
function isUserName(strInput) {
    var myReg = /^[a-zA-Z0-9_]{5,20}$/;
    if (myReg.test(strInput))
        return true;
    return false;
}
/**
 * 编号只能含有字母、数字和下划线，且长度在5到20位之内
 */
function isNO(strInput) {
    var myReg = /[a-zA-Z0-9_]/;
    if (myReg.test(strInput))
        return false;
    return true;
}
/**
 * 编号只能含有字母、数字和下划线。长度在1到8之间；
 */
function isUserNo(strInput) {
    var myReg = /^[a-zA-Z0-9_]{1,8}$/;
    if (myReg.test(strInput))
        return true;
    return false;
}
/**
 * 编号只能含有字母、数字和下划线。长度在1到20之间；
 */
function isStudentNo(strInput) {
    var myReg = /^[a-zA-Z0-9_]{1,20}$/;
    if (myReg.test(strInput))
        return true;
    return false;
}
/**
 * 英文验证
 */
function isEnglish(strInput) {
    var myReg = /^[a-zA-Z]{1,20}$/;
    if (myReg.test(strInput))
        return true;
    return false;
}
/**
 * 限制只能输数字
 */
function isNum(s) {
    var ex = /^[0-9]{1,}$/;
    return ex.test(s);
}

/**
 * 单精度浮点数检验
 */
function isFloat(num) {
    var regExp = /^[+-]?(0|[1-9]\d*)(\.\d+)?$/;
    return regExp.test(num);
}

/**
 * 是否是日期格式yyyy-mm-dd
 */
function isDate(strDate) {
    var myReg = /^\d{4}[\/\-]?((((0?[13578])|(1[02]))\-?((0?[1-9]|[0-2][0-9])|(3[01])))|(((0?[469])|(11))\-?((0?[1-9]|[0-2][0-9])|(30)))|(0?[2]\-?(0?[1-9]|[0-2][0-9])))$/;
    if (myReg.test(strDate))
        return true;
    return false;
}
/**
 * 正双精度浮点数检验 小数点前保留7位，小数点后保留2位
 */
function isDouble(num) {
    var regExp = /^(0|[1-9]\d{0,6})(\.\d{1,2})?$/;
    return regExp.test(num);
}
function isDoubles(num) {
    var regExp = /^(0|[1-9]\d{0,9})(\.\d{1,2})?$/;
    return regExp.test(num);
}
/**
 * 两日期比较
 */
function compareData(start, end) {

    var date1 = new Date((start.value.replace("-", "/")));
    var date2 = new Date((end.value.replace("-", "/")));

    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)) {
        if (date2 > date1) {
            return true;
        } else {
            return false;
        }
    }
    if ((navigator.userAgent.indexOf('Firefox') >= 0)) {
        if (date2 > date1) {
            return true;
        } else {
            return false;
        }
    }
}
/**
 * 去掉左右所有空格
 * 
 * @param string :
 *            待处理字符串
 */
function trim(string) {
    var expression = /(^\s*)|(\s*$)/g;
    return string.replace(expression, "");
}

/**
 * 去掉左边所有空格
 * 
 * @param string :
 *            待处理字符串
 */
function ltrim(string) {
    var expression = /(^\s*)/g;
    return string.replace(expression, "");
}

/**
 * 去掉右边所有空格
 * 
 * @param string :
 *            待处理字符串
 */
function rtrim(string) {
    var expression = /(\s*$)/g;
    return string.replace(expression, "");
}
/**
 * 验证是否为空字符串
 * 
 * @param string
 *            :待处理字符串
 */
function isNullString(string) {
    return string == null || trim(string) == "";
}
/**
 * 验证对象的值是否为空
 * 
 * @param object :
 *            待处理对象
 */
function isNullObjValue(object) {
    if (object) {
        var value = object.value;
        if (isNullString(value)) {
            object.value = "";
            return true;
        } else if (value != trim(value)) {
            object.value = trim(value);
        }
        return false;
    } else {
        return true;
    }
}
/**
 * 验证是否为汉字
 * 
 * @param string :
 *            待处理字符串
 */
function zhongwen(string) {
    var expression = /^[\u4e00-\u9fa5]+$/;
    return expression.test(string);
}
;


/**
 * 判断电话号码为数字
 * 
 * 
 */
function isPhone(s) {
    var ex = /^1[0-9]{10}$/;
    return ex.test(s);
}

/**
 * 判断座机号码
 * 
 * 
 */
function isCamera(s) {
    var ex = /^((\d{3,4}[-])?)((\d{7,8}){1})$/;
    return ex.test(s);
}


/**
 * 只能输入整形类型
 */
function isInt(str) {
    var reg = /^(-1\+)?\d+$/;
    if (reg.test(str))
        return true;
    else
        return false;
}
// 验证身份证号方法
function checkIDCard(idcard) {
    var Errors = new Array("验证通过!", "身份证号码位数不对!", "身份证号码出生日期超出范围或含有非法字符!", "身份证号码校验错误!", "身份证地区非法!");
    var area = {11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "xinjiang", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"}
    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    if (area[parseInt(idcard.substr(0, 2))] == null)
        alert(Errors[4]);
    switch (idcard.length) {
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;// 测试出生日期的合法性
            }
            else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;// 测试出生日期的合法性
            }
            if (ereg.test(idcard))
                return true;
            else
                alert("身份证不正确");
            break;
        case 18:
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;// 闰年出生日期的合法性正则表达式
            }
            else {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;// 平年出生日期的合法性正则表达式
            }
            if (ereg.test(idcard)) {
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1);
                if (M == idcard_array[17])
                    return true;
                else
                    alert("身份证不正确");
            }
            else
                alert("身份证不正确");
            break;
        default:
            alert("身份证不正确");
            break;
    }
}
// 去掉字符串头尾空格
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
/**
 * 检查输入的URL地址是否正确 输入:str 字符串 返回:true 或 flase; true表示格式正确
 */
function checkURL(str){
    if (str.match(/(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i) == null) {
        return false
    }
    else {
        return true;
    }
}
// 验证邮箱
function checkEmail(str) {
	var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
	if (re.test(str)) {
		return true;
	} else {
		return false;
	}
}
// 验证特殊字符验证格式
function checkTextDataForNORMAL(strValue)
{
// 特殊字符验证格式
var regTextChar = /([\*"\'<>\/])+/ ;
return !regTextChar.test(strValue);
}
// 验证数值型
function checkTextDataForNUMBER1(strValue)
{
var regTextNumber = /^[1-9]\d?\.\d+$/;
return regTextNumber.test(strValue);
}
// 验证数值型
function checkTextDataForNUMBER(strValue)
{
	var regTextNumber = /^(\d)*$/;
	return regTextNumber.test(strValue);
}
// 验证金额
function checkMoney1(strValue, strUnit)
{
var testMoney = eval_r("/^\\d+(\\.\\d{0," + (strUnit.length -1) + "})?$/");
return testMoney.test(strValue);
}
// 验证金额
function checkMoney(value)
{
	var mny = /^[1-9]d*.d*|0.d*[1-9]d*$/; 
	return mny.test(value);
}

// 验证浮点型
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}
// 验证手机号码
function checkTextDataForPHONE(strValue)
{
var regTextPhone = /^(\(\d+\))*(\d)+(-(\d)+)*$/;
return regTextPhone.test(strValue);
}
/*
 * "^\\d+$" //非负整数（正整数 + 0） "^[0-9]*[1-9][0-9]*$" //正整数 "^((-\\d+)|(0+))$"
 * //非正整数（负整数 + 0） "^-[0-9]*[1-9][0-9]*$" //负整数 "^-?\\d+$" //整数 "^\\d+("
 * //非负浮点数（正浮点数 + 0）
 * "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"
 * //正浮点数 "^((-\\d+(" //非正浮点数（负浮点数 + 0）
 * "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"
 * //负浮点数 "^(-?\\d+)(" //浮点数
 */
