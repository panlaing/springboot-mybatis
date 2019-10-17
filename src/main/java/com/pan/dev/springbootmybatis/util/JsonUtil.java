package com.pan.dev.springbootmybatis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author liang.pan
 * @Description: TODO
 * @date 2019/10/17 10:11
 */
public class JsonUtil {


    /**
     * xml转json字符串 注意:路径和字符串二传一另外一个传null<br>
     * 方 法 名：xmlToJson <br>
     * 创 建 人：h.j <br>
     * 创建时间：2017年5月10日 上午10:48:26 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param xmlPath xml路径(和字符串二传一,两样都传优先使用路径)
     * @param xmlStr  xml字符串(和路径二传一,两样都传优先使用路径)
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String xmlToJson(String xmlPath, String xmlStr) {
        SAXBuilder sbder = new SAXBuilder();
        Map<String, Object> map = new HashMap<String, Object>();
        Document document;
        try {
            if (xmlPath != null) {
                //路径
                document = sbder.build(new File(xmlPath));
            } else if (xmlStr != null) {
                //xml字符
                StringReader reader = new StringReader(xmlStr);
                InputSource ins = new InputSource(reader);
                document = sbder.build(ins);
            } else {
                return "{}";
            }
            //获取根节点
            Element el = document.getRootElement();
            List<Element> eList = el.getChildren();
            Map<String, Object> rootMap = new HashMap<String, Object>();
            //得到递归组装的map
            rootMap = xmlToMap(eList, rootMap);
            map.put(el.getName(), rootMap);
            //将map转换为json 返回
            return JSON.toJSONString(map);
        } catch (Exception e) {
            return "{}";
        }
    }

    /**
     * json转xml<br>
     * 方 法 名：jsonToXml <br>
     * 创 建 人：h.j<br>
     * 创建时间：2017年5月10日 上午11:09:26 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param json
     * @return String
     */
    public static String jsonToXml(String json) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            JSONObject jObj = JSON.parseObject(json);
            jsonToXmlstr(jObj, buffer);
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json转str<br>
     * 方 法 名：jsonToXmlstr <br>
     * 创 建 人：h.j <br>
     * 创建时间：2017年5月10日 下午12:02:17 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param jObj
     * @param buffer
     * @return String
     */
    public static String jsonToXmlstr(JSONObject jObj, StringBuffer buffer) {
        Set<Entry<String, Object>> se = jObj.entrySet();
        for (Iterator<Entry<String, Object>> it = se.iterator(); it.hasNext(); ) {
            Entry<String, Object> en = it.next();
            if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")) {
                buffer.append("<" + en.getKey() + ">");
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo, buffer);
                buffer.append("</" + en.getKey() + ">");
            } else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")) {
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<" + en.getKey() + ">");
                    JSONObject jsonobject = jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject, buffer);
                    buffer.append("</" + en.getKey() + ">");
                }
            } else if (en.getValue().getClass().getName().equals("java.lang.String")) {
                buffer.append("<" + en.getKey() + ">" + en.getValue());
                buffer.append("</" + en.getKey() + ">");
            }

        }
        return buffer.toString();
    }


    /**
     * 节点转map<br>
     * 方 法 名：xmlToMap <br>
     * 创 建 人：h.j <br>
     * 创建时间：2017年5月10日 上午10:56:49 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param eList
     * @param map
     * @return Map<String   ,   Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> xmlToMap(List<Element> eList, Map<String, Object> map) {
        for (Element e : eList) {
            Map<String, Object> eMap = new HashMap<String, Object>();
            List<Element> elementList = e.getChildren();
            if (elementList != null && elementList.size() > 0) {
                eMap = xmlToMap(elementList, eMap);
                Object obj = map.get(e.getName());
                if (obj != null) {
                    List<Object> olist = new ArrayList<Object>();
                    if (obj.getClass().getName().equals("java.util.HashMap")) {
                        olist.add(obj);
                        olist.add(eMap);

                    } else if (obj.getClass().getName().equals("java.util.ArrayList")) {
                        olist = (List<Object>) obj;
                        olist.add(eMap);
                    }
                    map.put(e.getName(), olist);
                } else {
                    map.put(e.getName(), eMap);
                }
            } else {
                map.put(e.getName(), e.getValue());
            }
        }
        return map;
    }


    public static void main(String[] args) {

        String a = "{\"Int_l_Plan\":\"no\",\"VMail_Plan\":\"yes\",\"VMail_Message\":\"33.0\",\"Day_Mins\":\"125.0\",\"Day_Calls\":\"99.0\",\"Day_Charge\":\"21.25\",\"Eve_Mins\":\"235.3\",\"Eve_Calls\":\"81.0\",\"Eve_Charge\":\"20.0\",\"Night_Mins\":\"215.3\",\"Night_Calls\":\"95.0\",\"Night_Charge\":\"9.69\",\"Intl_Mins\":\"10.2\",\"Intl_Calls\":\"7.0\",\"Intl_Charge\":\"2.75\",\"CustServ_Calls\":\"2.0\",\"creditability\":\"0.0\",\"SampleMark\":\"Test\",\"DayEve_Mins\":\"360.3\",\"DayEve_Calls\":\"180.0\",\"DayEve_Charge\":\"41.25\",\"ALLDay_Mins\":\"575.6\",\"ALLDay_Calls\":\"275.0\",\"ALLDay_Charge\":\"50.94\",\"Day_Mins_Ratio\":\"0.217164698\",\"Day_Calls_Ratio\":\"0.36\",\"Day_Charge_Ratio\":\"0.41715744\",\"Eve_Mins_Ratio\":\"0.408790827\",\"Eve_Calls_Ratio\":\"0.294545455\",\"Eve_Charge_Ratio\":\"0.392618767\",\"Night_Mins_Ratio\":\"0.374044475\",\"Night_Calls_Ratio\":\"0.345454545\",\"Night_Charge_Ratio\":\"0.190223793\",\"DayEve_Mins_Ratio\":\"0.625955525\",\"DayEve_Calls_Ratio\":\"0.654545455\",\"DayEve_Charge_Ratio\":\"0.809776207\",\"Intl_Mins_Ratio\":\"0.017720639\",\"Intl_Calls_Ratio\":\"0.025454545\",\"Intl_Charge_Ratio\":\"0.05398508\",\"CustServ_Calls_Ratio\":\"0.007272727\",\"Calls_Avg\":\"91.66666667\",\"Mins_Avg\":\"191.8666667\",\"Charge_Avg\":\"16.98\",\"Calls_Max\":\"99.0\",\"Mins_Max\":\"235.3\",\"Charge_Max\":\"21.25\",\"Calls_Min\":\"81.0\",\"Mins_Min\":\"125.0\",\"Charge_Min\":\"9.69\",\"Day_Mins_Avg\":\"1.262626263\",\"Day_Charge_Avg\":\"0.214646465\",\"Eve_Mins_Avg\":\"2.904938272\",\"Eve_Charge_Avg\":\"0.24691358\",\"Night_Mins_Avg\":\"2.266315789\",\"Night_Charge_Avg\":\"0.102\",\"DayEve_Mins_Avg\":\"2.001666667\",\"DayEve_Charge_Avg\":\"0.229166667\",\"ALLDay_Mins_Avg\":\"2.093090909\",\"ALLDay_Charge_Avg\":\"0.185236364\",\"Intl_Mins_Avg\":\"1.457142857\",\"Intl_Charge_Avg\":\"0.392857143\"}";
        String b="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Text><Int_l_Plan>no</Int_l_Plan><VMail_Plan>yes</VMail_Plan><VMail_Message>33.0</VMail_Message><Day_Mins>125.0</Day_Mins><Day_Calls>99.0</Day_Calls><Day_Charge>21.25</Day_Charge><Eve_Mins>235.3</Eve_Mins><Eve_Calls>81.0</Eve_Calls><Eve_Charge>20.0</Eve_Charge><Night_Mins>215.3</Night_Mins><Night_Calls>95.0</Night_Calls><Night_Charge>9.69</Night_Charge><Intl_Mins>10.2</Intl_Mins><Intl_Calls>7.0</Intl_Calls><Intl_Charge>2.75</Intl_Charge><CustServ_Calls>2.0</CustServ_Calls><creditability>0.0</creditability><SampleMark>Test</SampleMark><DayEve_Mins>360.3</DayEve_Mins><DayEve_Calls>180.0</DayEve_Calls><DayEve_Charge>41.25</DayEve_Charge><ALLDay_Mins>575.6</ALLDay_Mins><ALLDay_Calls>275.0</ALLDay_Calls><ALLDay_Charge>50.94</ALLDay_Charge><Day_Mins_Ratio>0.217164698</Day_Mins_Ratio><Day_Calls_Ratio>0.36</Day_Calls_Ratio><Day_Charge_Ratio>0.41715744</Day_Charge_Ratio><Eve_Mins_Ratio>0.408790827</Eve_Mins_Ratio><Eve_Calls_Ratio>0.294545455</Eve_Calls_Ratio><Eve_Charge_Ratio>0.392618767</Eve_Charge_Ratio><Night_Mins_Ratio>0.374044475</Night_Mins_Ratio><Night_Calls_Ratio>0.345454545</Night_Calls_Ratio><Night_Charge_Ratio>0.190223793</Night_Charge_Ratio><DayEve_Mins_Ratio>0.625955525</DayEve_Mins_Ratio><DayEve_Calls_Ratio>0.654545455</DayEve_Calls_Ratio><DayEve_Charge_Ratio>0.809776207</DayEve_Charge_Ratio><Intl_Mins_Ratio>0.017720639</Intl_Mins_Ratio><Intl_Calls_Ratio>0.025454545</Intl_Calls_Ratio><Intl_Charge_Ratio>0.05398508</Intl_Charge_Ratio><CustServ_Calls_Ratio>0.007272727</CustServ_Calls_Ratio><Calls_Avg>91.66666667</Calls_Avg><Mins_Avg>191.8666667</Mins_Avg><Charge_Avg>16.98</Charge_Avg><Calls_Max>99.0</Calls_Max><Mins_Max>235.3</Mins_Max><Charge_Max>21.25</Charge_Max><Calls_Min>81.0</Calls_Min><Mins_Min>125.0</Mins_Min><Charge_Min>9.69</Charge_Min><Day_Mins_Avg>1.262626263</Day_Mins_Avg><Day_Charge_Avg>0.214646465</Day_Charge_Avg><Eve_Mins_Avg>2.904938272</Eve_Mins_Avg><Eve_Charge_Avg>0.24691358</Eve_Charge_Avg><Night_Mins_Avg>2.266315789</Night_Mins_Avg><Night_Charge_Avg>0.102</Night_Charge_Avg><DayEve_Mins_Avg>2.001666667</DayEve_Mins_Avg><DayEve_Charge_Avg>0.229166667</DayEve_Charge_Avg><ALLDay_Mins_Avg>2.093090909</ALLDay_Mins_Avg><ALLDay_Charge_Avg>0.185236364</ALLDay_Charge_Avg><Intl_Mins_Avg>1.457142857</Intl_Mins_Avg><Intl_Charge_Avg>0.392857143</Intl_Charge_Avg></Text>";
        String xml = jsonToXml(a);
        String json = xmlToJson(null, b);
        System.out.println(xml);
        System.out.println(json);
    }

}
