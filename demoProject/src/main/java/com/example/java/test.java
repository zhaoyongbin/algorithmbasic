package com.example.java;


import com.example.java.jxl.test.StakeResultProcessor;
import com.example.java.jxl.test.Student;
import com.example.java.math.DoubleNode;
import com.example.java.math.Node;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        String a = "A股";
        String b = "B股";
        String c = "A股";
        System.out.println(a.contains(c));
        System.out.println(a.equals(c));

        BigDecimal aa = BigDecimal.valueOf(0);
        BigDecimal bb = null;
        System.out.println(aa.equals(BigDecimal.valueOf(0)));
        System.out.println(bb == null);

        List<String> stock = new ArrayList<>();
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        //lambda
        //Output : A,B,C,D,E
//        items.forEach(item->System.out.println(item));
        items.forEach(index -> {
            stock.add(index);
        });
        stock.forEach(ss -> {
            System.out.println(ss);
        });


    }


    @Test
    public void test111() {
//        List<ManagerBean> managerListNew = new ArrayList<ManagerBean>();
//        if (mans != null ){
//            mans.forEach(man->{
//                managerListNew.add(man);
//            });
//        }
        //////
        List<String> stock = new ArrayList<>();
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        //lambda
        //Output : A,B,C,D,E
        items.forEach(item -> System.out.println(item + "赵永彬"));
        items.forEach(index -> {
            stock.add(index);
        });

        stock.forEach(ss -> {
            System.out.println(ss);
        });

//        list.forEach(System.out::println);
    }

    @Test
    public void test1() {
        Integer a = 1554;
        BigDecimal bid = new BigDecimal(12.235);
        BigDecimal divia = bid.divide(BigDecimal.valueOf(2));
        System.out.println(divia.setScale(3, RoundingMode.HALF_UP));
        System.out.println(divia);
        System.out.println(BigDecimal.ZERO);

        double payPercent = 123.345;
        BigDecimal bg = new BigDecimal(payPercent).setScale(2, RoundingMode.HALF_UP);
        System.out.println(bg.doubleValue());


        double payPercent1 = 123.345;
        BigDecimal bg1 = new BigDecimal(String.valueOf(payPercent1)).setScale(2, RoundingMode.HALF_UP);
        System.out.println(bg1.doubleValue());

        double payPercent3 = 123.345;
        BigDecimal bg3 = new BigDecimal(String.valueOf(payPercent3)).setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(bg3.doubleValue());
//四舍五入
        BigDecimal bg4 = new BigDecimal(String.valueOf(payPercent3)).setScale(2, BigDecimal.ROUND_HALF_UP);

//        a.divi
//        (double)(Math.round(10.22*1000)/10000.0);
    }

    @Test
    public void test2() {
//        double aa=10;
//        double bb=23;
//        double cc = Math.abs(a- b);
//        System.out.println(c);

        BigDecimal aa = new BigDecimal(2135264546);
        BigDecimal bb = new BigDecimal(46);
        BigDecimal vv = bb.subtract(aa);
        BigDecimal vv1 = aa.subtract(bb);
        System.out.println(vv);
        System.out.println(vv1);
        System.out.println(Math.abs(vv.doubleValue()) + "we" + Math.abs(vv1.doubleValue())); //2.1323546E9
        double sa = Math.abs(vv1.doubleValue());
        System.out.println(BigDecimal.valueOf(sa).divide(bb, 11, BigDecimal.ROUND_HALF_UP));

        BigDecimal a = new BigDecimal("11111111111111111");
        BigDecimal b = new BigDecimal(4654);
        System.out.println(a.add(b));     //a+b  加
        System.out.println(a.subtract(b)); // a-b 减
        System.out.println(a.multiply(b)); // a*b 乘
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_HALF_UP));// a/b 除

        BigDecimal c = new BigDecimal("5466.6162");
        BigDecimal d = new BigDecimal(2);
        System.out.println(c.divide(d));//
        System.out.println(c.divide(b, 2, BigDecimal.ROUND_HALF_UP));//

    }


    @Test
    public void test3() {
        String a = "";
        System.out.println(a.concat("566565"));
        for (int i = 0; i < 5; i++) {
            a.concat("sss");
            System.out.println(a);
        }
        System.out.println(a);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            str.append("sss");
        }
        System.out.println(str);
    }

    @Test
    public void test4() {

        Map<String, String> mapa = new HashMap<String, String>();
        mapa.put("1", "st");
        mapa.put("2", "sde");
        boolean bb = mapa.containsKey("1");
        boolean cc = mapa.containsKey("3");
        System.out.println(cc);
        System.out.println(bb);


        String str = "dsrtsdfsd";
        String reg = "[0-9A-Za-z]{24,}"; // appear at least 24 times ^[0-9A-Za-z]{24,}
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);


        /* 源码：
         * @param   regex : 此字符串可以匹配正则表达式，也可以是一般字符
         * @param   replacement : 要替换成的字符串
         */
      /*  public String replaceAll(String regex, String replacement){
            return Pattern.compile(regex).matcher(this).replaceAll(replacement);
        }*/
    }


    @Test
    public void lambdaTest1() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三"));
        list.add(new User(4, "赵六"));
        list.add(new User(2, "李四"));
        list.add(new User(3, "王五"));


        list.forEach(f -> f.setUserName("zhao"));
        System.out.println(list);
//        list.forEach（f  - > f.setName（f.getName（ ）+"s"））

        //条件删除
//        list.removeIf(user -> user.getUserId() == 3);
        System.out.println("--------------排序前");
        list.forEach(user -> {
            System.out.println(user);
        });
//        list.sort((user1,user2)->user1.getUserId() - user2.getUserId());
        System.out.println("_-------------------正序排序后");
        list.forEach(user -> {
            System.out.println(user);
        });

        System.out.println("--------------------倒序排序后：");
//        list.sort((((o1, o2) -> o2.getUserId() - o1.getUserId())));
        list.forEach(user -> {
            System.out.println(user);
        });

//        Collection
    }

    @Test
    public void lambdaTest11() {
    /*    String a="123456789";
        System.out.println(a.startsWith("23"));
        System.out.println(a.startsWith("12"));
        System.out.println(a.startsWith("123"));
        List<String> aa=new ArrayList<>();
        System.out.println(aa);
        System.out.println(aa.size());*/
        List<String> list = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("2");
                add("3");
            }
        };


        List<String> list1 = new ArrayList<String>() {
            {
                add("4");
                add("5");
                add("6");
                add("6");
            }
        };
        int i = 0;
        for (String aa : list) {
            String bb = list1.get(i);
            System.out.println(bb);
            i += 1;

        }
        list.forEach(aa -> {
        });

        List<String> aa = list.stream().filter(String -> !list1.contains(String)).collect(Collectors.toList());
        if (aa.size() == 0) {
            System.out.println("zhaoyongbin");
        }
        boolean istrue = list.removeAll(list1);
        System.out.println(istrue);
        //差集
        List<String> sssss = list.stream().filter(String -> !list1.contains(String)).collect(Collectors.toList());
        System.out.println(sssss);

    }

    @Test
    public void bigDecimaltest() {
        System.out.println(new BigDecimal(2).setScale(4, BigDecimal.ROUND_HALF_UP));
        List<User1> list = new ArrayList<User1>();
        /*list.add(new User1("zhao",0,"123","其"));
        list.add(new User1("zhao",0,"123","其"));
        list.add(new User1("zhao",0,"123","其"));
        list.add(new User1("zhao",0,"123","其"));
        list.add(new User1("zhao",1,"123","其"));*/
        list.add(new User1("zhao", 1, "000002", "万科A"));
        list.add(new User1("zhao", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao", 0, "000004", "f"));
        list.add(new User1("zhao123", 0, "000006", "深圳业A"));
        list.add(new User1("zhao123", 1, "000006", "AA"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁"));

/*
        list.forEach(user ->{
            System.out.println(user);
        });*/
        // 按userId升序、username降序、birthDate升序排序
        String[] sortNameArr = {"age"}; //,"stockName"
        boolean[] isAscArr = {false};//,false
        ListUtils.sort(list, sortNameArr, isAscArr);

  /*  list.sort((user1,user2)->user1.age-user2.getAge());
    list.forEach(u->{
        System.out.println(u);
    });*/

        //list.sort((user1,user2)->user2.age-user1.getAge());
        list.forEach(u1 -> {
            System.out.println(u1);
        });

        BigDecimal a = new BigDecimal(0.25);
        BigDecimal b = new BigDecimal(0);
        BigDecimal c = BigDecimal.valueOf(0);

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.floatValue() == 0);
        System.out.println(a.intValue() == 0);

        System.out.println(b == BigDecimal.ZERO);

        System.out.println(b == BigDecimal.valueOf(0));
        System.out.println(BigDecimal.ZERO);
        System.out.println(c == BigDecimal.valueOf(0));
        System.out.println(c == BigDecimal.ZERO);


    }

    @Test
    public void groupby() {
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao", 1, "000002", "万科A"));
        list.add(new User1("zhao", 1, "000002", "万科A12"));
        list.add(new User1("zhao", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao", 0, "000004", "万科Asdfdf"));
        list.add(new User1("zhao123", 0, "000006", "深圳业A"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁213"));
        List<User1> list2 = new ArrayList();
        Map<String, List<User1>> listMap = list.stream().collect(Collectors.groupingBy(User1::getStockCode));
        for (String key : listMap.keySet()) {
            if (listMap.get(key).size() > 1) {
                list2.addAll(listMap.get(key));
            }
        }

        for (Map.Entry<String, List<User1>> user : listMap.entrySet()) {
            user.getValue().size();
        }

// list转map
//        Map<String, String> sd = list.stream().collect(Collectors.toMap(User1::getName, User1::getStockCode));
        Map<String, List<User1>> sdsd = list.stream().collect(Collectors.groupingBy(user1 -> user1.getName() + "#" + user1.getStockCode() + "#" + user1.getStockName()));


        List<List<User1>> list1 = new ArrayList<>(listMap.values());
        list1.forEach(u -> {
            System.out.println(u);
        });
        // 按userId升序、username降序、birthDate升序排序
        String[] sortNameArr = {"age"};
        boolean[] isAscArr = {false};
        ListUtils.sort(list1, sortNameArr, isAscArr);
        list1.forEach(u -> {
            System.out.println(u);
        });
        Long a = null;
        BigDecimal.valueOf(a);

        //System.out.println(list);
    }


    @Test
    public void startwith() {
//        BigDecimal a = new BigDecimal(null);
        String a = "sad";
//        boolean s = a.startsWith(null);
        String b = "fgdf";
        boolean v = b.startsWith(a);
        boolean sdsd = a.endsWith("d");
        System.out.println(v);

        String sds = "12356#102";
        String fff = sds.substring(sds.indexOf("#") + 1, sds.length());
        System.out.println(fff);
    }

    @Test
    public void testnull() {
        List<String> a = null;
        a.add("sdsd");

        if (null == null) {
            System.out.println("testc测试通过");
        }
    }

    @Test
    public void getlistElement() {
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao1", 1, "000002", "万科A"));
        list.add(new User1("zhao1", 1, "000002", "万科A12"));
        list.add(new User1("zhao2", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));
        list.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));
        List<String> orderNoList = list.stream().map(User1::getName).collect(Collectors.toList());
        for (String s : orderNoList) {
            System.out.println(s);
        }
        System.out.println("=================");
        List<String> listWithoutDuplicates = orderNoList.stream().distinct().collect(Collectors.toList());
        for (String o : listWithoutDuplicates) {
            System.out.println(o);
        }
        System.out.println("--------list根据对象某一字段值去重--------");
        List<User1> userList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User1::getName))), ArrayList::new));
        userList.forEach(u -> {
            System.out.println(u);
        });

        double a = 25 / 2;
        System.out.println(a);
        System.out.println("删除元素");
        Iterator<User1> it = list.iterator();
        while (it.hasNext()) {
            User1 x = it.next();
            if (x.getName().equals("zhao1")) {
                it.remove();
            }
        }

        /* for(User1 u:list){
             if(u.getName().equals("zhao1")){
                 list.remove(u);
             }
         }*/
        System.out.println(list);

    }

    @Test
    public void getsubtract() {
        BigDecimal s = new BigDecimal(10000);
        BigDecimal bb = new BigDecimal(10);
        BigDecimal c = new BigDecimal(10);
        c = null;
        BigDecimal a = s.subtract(BigDecimal.valueOf(0));
        BigDecimal ff = (s.multiply(bb).multiply(c)).divide(BigDecimal.valueOf(10), 3, BigDecimal.ROUND_HALF_UP);
        System.out.println(ff);
        System.out.println(a);
    }


    /* public void jxl() throws IOException {
 //        @Override
         public void exportMaterialBomExcel(final String bomId, final HttpServletResponse response) throws IOException, InvalidFormatException, Exception {
             //获取模板文件
             final InputStream is = this.getInputStream();

             final Map<String, Object> returnMap = new HashMap<>();
             final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             final List<String> nameList = new ArrayList<>();
             //工艺数据
             final List<BomManufactureTitle> titleList = bomManufactureTitleService`.getBomManufactureTitleName(bomId);
             titleList.forEach(entity -> {
                 nameList.add(entity.getName());
                 //格式化时间
                 entity.setCreateTime(format.format(entity.getCreateDate()));
                 //审批数据
                 final List<BomApproval> approvalList = bomApprovalService.listByWrapper(new EntityWrapper<BomApproval>().eq("BOM_ID", entity.getId()));
                 entity.setApprovalList(approvalList.size() == 0 ? null : approvalList.get(0));
                 //物料详情数据
                 entity.setMaterialList(bomManufactureMaterialService.listByWrapper(new EntityWrapper<BomManufactureMaterial>().
                         eq("MBOM_CODE", entity.getCode()).ge("MAX_VERSION", entity.getVersion()).le("MIN_VERSION", entity.getVersion()).orderBy("FEED_NO")));
             });

             returnMap.put("titleList", titleList);
             //将文件放置到客户端下载
             this.setResponse(response);
             //excel表格导出
             this.exportExcel(is, response.getOutputStream(), returnMap);

         }


         private InputStream getInputStream() throws FileNotFoundException {
             final URL url = this.getClass().getClassLoader().getResource("bom/bom.xls");
             return new FileInputStream(url.getFile());
         }

         private void setResponse(final HttpServletResponse response) {
             response.reset();
             response.setContentType("application/octet-stream; charset=utf-8");
             response.setHeader("Content-Disposition", "attachment; filename=" + "xlsxExport.xls");
         }

         public static void exportExcel(final InputStream is, final OutputStream os,final Map<String, Object> model) throws IOException {
             final Context context = PoiTransformer.createInitialContext();
             if (model != null) {
                 for (final String key : model.keySet()) {
                     context.putVar(key, model.get(key));
                 }
             }
             final JxlsHelper jxlsHelper = JxlsHelper.getInstance();
             final Transformer transformer = jxlsHelper.createTransformer(is, os);
             //必须要这个，否者表格函数统计会错乱
             jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);

         }

     }*/
    @Test
    public void multMAP() {
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao1", 1, "000002", "万科A"));
        list.add(new User1("zhao1", 1, "000002", "万科A12"));
        list.add(new User1("zhao2", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));
        list.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));


        list.forEach(u -> {
            u.setAge(12);
        });
        System.out.println("skdhjfg" + list.toString());


        List<User1> list2 = new ArrayList<User1>();
        list2.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));
        list2.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));
        ListMultimap<String, List<User1>> myMultiMap = ArrayListMultimap.create();
        myMultiMap.put("1", list);
        myMultiMap.put("2", list2);
        List<List<User1>> sd = myMultiMap.get("1");
        for (List<User1> ff : sd) {
            System.out.println(ff);
        }

        Map<String, String> map = new HashMap<>();
        System.out.println(map.isEmpty());
        System.out.println(map.get("sdsfd"));
        map.put("", "");
        System.out.println("1".equals(map.get("")));

        boolean dfd = !map.isEmpty();
        System.out.println(map.get(""));
        System.out.println(dfd);

        boolean tis = false;
        User1 us = new User1();
        us.setCurr(false);
        System.out.println();

    }

    //list 取交集
    @Test
    public void intersection() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("3");
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println(intersection);

        Iterator<String> it = list1.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (str.equals("1")) {
                it.remove();
            }
        }
        System.out.println(list1);

    }


    @Test
    public void listTEST() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2");
        System.out.println(list);

        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        set.add("2");
        System.out.println(set.toString());

    }

    @Test
    public void stringCoverToInt() {
        int a = Integer.parseInt("");
        System.out.println(a);
    }

    @Test
    public void replaceAbbllTest() {
        String aa = "1231,123123，sdfs,sdff，46568";
        System.out.println(aa);
        String bb = aa.replaceAll("，", ",");
        String[] split = bb.split(",");
        System.out.println(split.length);
        for (String code : split) {
            System.out.println(code);
            System.out.println(code.replaceAll("\'", ""));
        }

        System.out.println(bb);
    }


    @Test
    public void collectionUtilTest() {
        List<String> list = null;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<String>() {
            {
                add("sd");
            }
        };
        boolean aa = CollectionUtils.isEmpty(list);
        boolean bb = CollectionUtils.isEmpty(list1);
        boolean cc = CollectionUtils.isEmpty(list2);
        System.out.println(aa);
        System.out.println(bb);
        System.out.println(cc);
        String sd = null, sd1 = null, sd2 = "";
        System.out.println(sd);
        System.out.println(sd1);
        System.out.println(sd2);

    }

    @Test
    public void removeIf() {
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("1");
        list.add("2");
        list.removeIf(s -> s.contains("1"));
        System.out.println(list);
    }

    @Test
    public void testand() {
        String a = "1";
        String b = "2";
        System.out.println(true && !("1".equals(a) && "1".equals(b)));
        System.out.println(true && ("1".equals(a) && "1".equals(b)));
    }

    List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Test
    public void testMultiply() {
        BigDecimal b = new BigDecimal(1.2356);

        System.out.println(b.multiply(new BigDecimal(1)).multiply(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP));

        test tt = new test();
        System.out.println(tt.getList().size());
        tt.getList().add("aa");
        System.out.println(tt.getList());


    }


    @Test
    public void testif() {
        String woring = "", sds = "sdf";
        if (false) woring = "sdfsd";
        System.out.println(woring);
        System.out.println(sds);

        System.out.println("案发后答复");
    }

    @Autowired
    private StakeResultProcessor stakeResultProcessor;

    @Test
    public void divkInetrface() {
        System.out.println(Arrays.asList(list).contains("aa"));
        System.out.println(list.contains("123"));

    }

    @Test
    public void nulltostring() {
        List<String> list = new ArrayList<>();
        list.add("sd");
        System.out.println(list.toString());
        System.out.println(list.size());
    }

    @Test
    public void setTest() throws ParseException {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        set.add("2");
        set.add("3");
        System.out.println(set.toString());
        for (String str : set) {
            System.out.println(str);
        }

        Set<Date> set1 = new HashSet<>();

        set1.add(new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-12"));
        set1.add(new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-12"));
        set1.add(new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-11"));
        set1.add(new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-10"));
        System.out.println(set1.toString());
        for (Date str : set1) {
            System.out.println(str);
        }

    }


    //list多属性去重  辩解
    @Test
    public void listquchong() {
//       List<ClassEntity> distinctClass = classEntities.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getProfessionId() + ";" + o.getGrade()))), ArrayList::new));
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao1", 1, "000002", "万科A"));
        list.add(new User1("zhao1", 1, "000002", "万科A12"));
        list.add(new User1("zhao2", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));
        list.add(new User1("zhao3", 0, "000004", "万科Asdfdf"));

//       classEntities.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getProfessionId() + ";" + o.getGrade()))), ArrayList::new));
        List<User1> list1 = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(u -> u.getName() + ";" + u.getAge()))), ArrayList::new));
        list1.forEach(u -> {
            System.out.println(u);
        });
    }

    @Test
    public void testdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date);
        System.out.println(date.toString());
//       System.out.println(sdf.format(date,"yyyy-MM-dd"));
    }

    @Test
    public void testString() {
        System.out.println(String.valueOf(0));
        Map<String, String> map = new HashMap<>();
        map.put("aa", String.valueOf(0));
        System.out.println(map.get("aa"));
        System.out.println("".equals(map.get("aa")));
        System.out.println("0".equals(map.get("aa")));
        System.out.println(0 == Integer.parseInt(map.get("aa")));
    }

    @Test
    public void strboole() {
        System.out.println("1".equals(null));
    }

    @Test //取差集
    public void fork() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("3");

        List<String> aa = list1.stream().filter(String -> !list2.contains(String)).collect(Collectors.toList());
        System.out.println(aa);
        System.out.println(list1.contains("1"));

        List<String> list3 = new ArrayList<>();
        list3.add("3");
        list3.add("2");
        List<String> list4 = new ArrayList<>();
        list4.add("4");
        list4.add("3");
        List<String> bb = list3.stream().filter(String -> !list4.contains(String)).collect(Collectors.toList());
        System.out.println(bb);

    }

    @Test //去重
    public void qufork() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("2");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("3");

        List<String> aa = list1.stream().distinct().collect(Collectors.toList());

        System.out.println(aa);

        Student s1 = new Student(11, "black", 456);
        Student s2 = new Student(12, "red", 123);//old

        Student s3 = new Student(11, "black", 123);
        Student s4 = new Student(12, "red1", 123);
        List<Student> list3 = new ArrayList();
        list3.add(s1);
        list3.add(s2);
        list3.add(s3);
        list3.add(s4);
        List<Student> userList = list3.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getHaircolor))), ArrayList::new));
        userList.forEach(u -> System.out.println(u.toString()));
        System.out.println("userList==============");
        list3.forEach(u -> System.out.println(u.toString()));


    }

    @Test //
    public void fork1() {
        List<String> list = new ArrayList<>();
        System.out.println(list.size());

        List<String> list1 = null;
        System.out.println(list1.size());
    }

    @Test
    public void sanmutes() {

        String aa = "asd".equals("asd1") ? "aaa" : "asd";
//        System.out.println(aa);
        StringBuilder str = new StringBuilder();
        str.append("1");
        str.append("2");
        String tt = "sd";
        String tt1 = "aa";
        System.out.println(tt + str + tt1);

    }


    @Test
    public void bigmuber() {
        BigDecimal big = new BigDecimal(1);
        BigDecimal big2 = new BigDecimal(0);
        BigDecimal big1 = null;
        if (StringUtils.isEmpty(big)) {
            System.out.println(big);
        }

        System.out.println(big);
        System.out.println(big2);
        System.out.println(big1);
    }

    @Test
    public void stringbuilder() {
        StringBuilder str = new StringBuilder();
        str.append("赵永彬");
        if (str.length() > 0 && !"null".equals(str.toString()) && !"".equals(str.toString())) {
            System.out.println("str不为空");
        }

        System.out.println(StringUtils.isEmpty(str));
        StringBuffer st = new StringBuffer();
        System.out.println(StringUtils.isEmpty(st));
        st.append("123");
        st.append("sdf");
        System.out.println(st);

        String aa = "";
        System.out.println(StringUtils.isEmpty(aa));
        aa = "er";
        String as = aa.concat("sds");
        System.out.println(as);
        System.out.println(aa);
    }

    @Test
    public void removeIt() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("CN4242");
        System.out.println(list1.toString());
        boolean s = list1.removeIf("1"::equals);
        list1.removeIf("2"::equals);
        System.out.println(list1.toString());

    }

    @Test
    public void Tmultiply() {
        List<String> st = new ArrayList<>();
        st.add("1");
        st.clear();
        if (st.size() == 0) {
            System.out.println("sdsfdssssssss");
        }
        BigDecimal big = new BigDecimal(1000);
        BigDecimal as = big.multiply(null);
        //你知道吗  买书也很有乐趣 ，买回来可能会看几天 过几天就不想看了 ，有时候想起来了 就又看看   但是就是有欲望要买回来
    }

    @Test
    public void testKEY() {
        User user = new User();
        User user2 = new User();
        Map<String, User> map = new HashMap<>();
        map.put("u1", user);
        map.put("u2", user2);

        boolean sd = StringUtils.isEmpty(map.get("u"));
        boolean sd1 = StringUtils.isEmpty(map.get("u1"));

        System.out.println(sd);
        System.out.println(sd1);


        System.out.println(null == user);
        System.out.println("".equals(user));

    }


    @Test
    public void testStringUtils() {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = null;
        System.out.println(StringUtils.isEmpty(map));
        System.out.println(StringUtils.isEmpty(map1));
        System.out.println(StringUtils.isEmpty(null));


    }


    @Test
    public void testForkClass() {
        Student s1 = new Student(11, "black", 456);
        Student s2 = new Student(12, "red", 123);//old
        Student s6 = new Student(13, "red", 123);//old

        Student s3 = new Student(11, "black", 123);
        Student s4 = new Student(12, "red1", 123);
        Student s5 = new Student(15, "white", 123);//new

        List<Student> oldList = new ArrayList<Student>() {{
            add(s1);
            add(s2);
            add(s6);
        }};
        List<Integer> oldAgeList = oldList.stream().map(x -> x.getAge()).collect(Collectors.toList());

        List<Student> newList = new ArrayList<Student>() {{
            add(s3);
            add(s4);
            add(s5);
        }};
        //获取元素对象属性值集合
        List<Integer> newAgeList = oldList.stream().map(x -> x.getAge()).collect(Collectors.toList());

        //List<Bean> 根据Bean的一个属性求两个list的交集 差集
        //交集
        List<Student> updList = newList.stream()
                .filter(item -> oldList.stream()
                        .map(e -> e.getAge())
                        .collect(Collectors.toList())
                        .contains(item.getAge()))
                .collect(Collectors.toList());
        System.out.println(updList);

        // 差集 (new - old)
        List<Student> addList = newList.stream()
                .filter(item -> !oldList.stream()
                        .map(e -> e.getAge())
                        .collect(Collectors.toList())
                        .contains(item.getAge()))
                .collect(Collectors.toList());
        System.out.println(addList);

        // 差集 (old - new)
        List<Student> delList = oldList.stream()
                .filter(item -> !newList.stream()
                        .map(e -> e.getAge())
                        .collect(Collectors.toList())
                        .contains(item.getAge()))
                .collect(Collectors.toList());
        System.out.println(delList);
        //list<String> 和 List<Bean> 取差集
        List<String> list3 = new ArrayList<String>() {{
            add("aa");
            add("black");
            add("black");
        }};
        List<String> listStr = list3.stream()
                .filter(item -> !newList.stream()
                        .map(e -> e.getHaircolor())
                        .collect(Collectors.toList())
                        .contains(item))
                .collect(Collectors.toList());
        System.out.println(listStr);

        //多字段排序
        ArrayList<Student> oldList1 = oldList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(table -> table.getAge() + ";" + table.getNo()))), ArrayList::new));

        //List<Bean> 根据Bean的2个属性求两个list的交集 差集？？
        List<String> list5 = new ArrayList<String>();
        List<String> list6 = new ArrayList<String>();
        List<String> bb = list5.stream().filter(String -> !list6.contains(String)).collect(Collectors.toList());


    }

    @Test
    public void tsetfork() {
        List<String> list5 = new ArrayList<String>();
        list5.add("sss");
        List<String> list6 = new ArrayList<String>();
//        list6.add("ss");
        List<String> bb = list5.stream().filter(String -> !list6.contains(String)).collect(Collectors.toList());
        System.out.println(bb);
    }


    @Test
    public void testCHongfu() {
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao", 1, "000002", "万科A"));
        list.add(new User1("zhao", 1, "000002", "万科A"));
        list.add(new User1("zhao", 1, "000002", "万科A12"));
        list.add(new User1("zhao", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao", 0, "000004", "万科Asdfdf"));
        list.add(new User1("zhao123", 0, "000006", "深圳业A"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁213"));

        //list 转成 map ;key:name  value:stockCode
        Map<String, String> userMap = list.stream().collect(Collectors.toMap(User1::getName, User1::getStockCode, (k1, k2) -> k2));
        Map<String, String> userMap1 = list.stream().collect(Collectors.toMap(User1::getName, User1::getStockCode, (k1, k2) -> k2));


        List<String> uniqueList = list.stream().collect(Collectors.groupingBy(User1::getStockCode, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(uniqueList);
//     System.out.println(uniqueList.getClass());
        uniqueList.forEach(p -> System.out.println(p));
        System.out.println("===================");
//获取重复记录的数据
        List<String> uniqueList2 = list.stream().collect(Collectors.groupingBy(user1 -> user1.getStockCode() + "#" + user1.getAge() + "#" + user1.getStockName(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        for (String str : uniqueList2) {
            int index1 = str.indexOf("#");
            int index2 = str.indexOf("#", index1 + 1);
            String ss = str.substring(0, index2);
            String sdsd = "证券代码:" + ss.substring(0, ss.indexOf("#")) + ",指数代码:" + ss.substring(ss.indexOf("#") + 1);
            System.out.println(sdsd);
        }


        uniqueList2.forEach(p -> System.out.println(p));
//    for (String str: uniqueList2){
//        str.substring(0, str.indexOf("#"));
//    }

        uniqueList2.forEach(p -> {
            String ss = p.substring(0, p.indexOf("#"));
            System.out.println(ss);

        });

        System.out.println("============####################=======");

        List<User1> unique = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User1::getName))), ArrayList::new));
        unique.forEach(p -> System.out.println(p));

        //过滤
        List<User1> ss = list.stream().filter(user -> (user.getName().equals("zhao") && user.getAge() == Integer.valueOf(1))).collect(Collectors.toList());
        List<User1> ss1 = list.stream().filter(user -> user.getName().equals("zhao") && user.getStockCode().equals("000002")).collect(Collectors.toList());
        System.out.println(ss.toString());

//     List<PbListBlack> smsBlackListSet = allBlacklist.stream().filter(sms -> (sms.getCorpcode().equals(corpCode) &&
//             sms.getBltype().equals(MessageTypeEnum.SMS.getKey()) && sms.getSvrtype().equals(bus))).collect(Collectors.toList());
//// 解释：本次对allBlacklist集合过滤条件三个：sms.getCorpcode()，sms.getBltype()，sms.getSvrtype()。
//     过滤判断条件是布尔类型的，所以多个条件的连接需要⽤&&（与） 、|| （或）连接符连接起来。最后筛选出符合条件的数据”的文档


//     java8去重(根据年级和专业,当年级和专业都相同的情况下看做是重复数据)
//     List<ClassEntity> distinctClass = classEntities.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getProfessionId() + ";" + o.getGrade()))), ArrayList::new));

//     List<CatTest> catList = Lists.newArrayList();
//     catList.stream()
//             .collect(
//                     Collectors.collectingAndThen(
//                             Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(cat -> cat.getCarHobby() + cat.getCatName()))),
//                             ArrayList::new
//                     )
//             );
    }

    @Test
    public void testiteror() {
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao1", 1, "000002", "万科A"));
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
        list.add(new User1("zhao3", 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao4", 0, "000004", "万科Asdfdf"));
        list.add(new User1("zhao123", 0, "000006", "深圳业A"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁"));
        list.add(new User1("zhao123", 1, "000008", "神州高铁213"));

        List<String> list1 = new ArrayList() {
            {
                add("zhao1");
                add("zhao2");
                add("zhao3");
                add("zhao4");

            }
        };


        Iterator sd = list.iterator();
        for (String str : list1) {
            while (sd.hasNext()) {
                User1 sss = (User1) sd.next();
                if (str.equals(sss.getName())) {
                    sd.remove();
                }
            }

            Iterator sd1 = list.iterator();
            while (sd1.hasNext()) {
                User1 sss1 = (User1) sd1.next();
                if (str.equals(sss1.getName())) {
                    sd1.remove();
                }
            }

        }

        System.out.println(list);
    }


    @Test
    public void AnnotationTest() {
//      //获取类注解
        Service userAnnou = AnnotationUtils.findAnnotation(User.class, Service.class);

        System.out.println("@SingletonComponent : " + userAnnou);
        System.out.println("@SingletonComponent value: " + AnnotationUtils.getValue((Annotation) userAnnou, "value"));
        System.out.println("@SingletonComponent value: " + AnnotationUtils.getDefaultValue((Annotation) userAnnou, "value"));
        LinkedList link = new LinkedList();


    }

    //回文字符串
    @Test
    public void strhuiwen() {
        System.out.println(check("nonon"));
    }

    public boolean check(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        int i = 0;
        int j = str.length() - 1;
        String[] strings = str.split("");
        for (i = 0; i <= j; i++, j--) {
            if (!strings[i].equals(strings[j])) {
                return false;
            }
        }
        return true;
    }


    public static final int config_one = 1 / 2 * 60 * 1000;
    public static final int config_one22 = (1) / 2 * 60;
    public static final int config_one11 = 1 * 60 * 1000;

    @Test
    public void testsssss() {
        System.out.println(config_one);
        System.out.println(config_one11);
        System.out.println(config_one22);
    }


    @Test
    public void teststest() {
        Node head = new Node(0);
        Node nextNode;
        nextNode = head;

        for (int i = 1; i < 10; i++) {
            Node node = new Node(i);
            nextNode.setNext(node);
            nextNode = nextNode.getNext();
        }

        nextNode = head;
        System.out.println(nextNode.toString());

    }

    @Test
    public void doubleintes() {
        double a = 0.000;
        System.out.println(a == Double.valueOf(0));

        String aa = "昭公猴哦哦规划和";
        System.out.println(aa.contains("公"));
        System.out.println(aa.contains("哦哦aas"));

        boolean istrue = true;
        System.out.println(true == istrue);

    }

    @Test
    public void truess() {

        boolean istrue = true;
        System.out.println(true == istrue);
        System.out.println(false == istrue);

        LinkedList link = new LinkedList<>();
    }

    @Test
    public void listTomap() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao1", 1, "000002", "万科A"));
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
        list.add(new User1(null, 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao4", 0, "000004", "万科Asdfdf"));
        Map<String, String> userMap1 = list.stream().collect(Collectors.toMap(User1::getName, User1::getStockCode, (k1, k2) -> k2));
        Map<String, User1> list2 = list.stream().collect(Collectors.toMap(User1::getName, Function.identity(), (key1, key2) -> key2));

        Map<String, User1> list3 = list.stream().collect(Collectors.toMap(item -> item.getName() + item.getAge(), Function.identity(), (key1, key2) -> key2));
        Map<String, List<User1>> list4 = list.stream().collect(Collectors.groupingBy(t -> t.getName()));

        for (Map.Entry<String, User1> inser : list2.entrySet()) {
            System.out.println(inser.getKey() + "::" + String.valueOf(inser.getValue()));
        }

        for (String ii : list2.keySet()) {
            System.out.println(ii);
        }

        stopWatch.stop();
        System.out.println(stopWatch.currentTaskName());
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getTotalTimeSeconds() / 60);

    }


    @Test
    public void remoetest() {
        List<User1> list = new ArrayList<User1>();
        list.add(new User1("zhao1", 1, "000002", "万科A"));
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
        list.add(new User1(null, 0, "000003", "万科Assdsfdfdfdf"));
        list.add(new User1("zhao4", 0, "000004", "万科Asdfdf"));
        List<String> list2 = new ArrayList<String>() {
            {
                add("zhao1");
                add("zhao2");
            }
        };

        for (String sec : list2) {
            for (int i = 0; i < list.size(); i++) {
                if (sec.equals(list.get(i).getName())) {
                    list.remove(i);
                    i--;
                }

            }
        }
        System.out.println(list.toString());
    }

    @Test
    public void listSeqces() {
        List<User1> list = new ArrayList<User1>();
        List<User1> list2 = new ArrayList<User1>();
//        list.add(new User1("zhao1", 1, "000002", "万科A"));
//        list.add(new User1("zhao2", 1, "000002", "万科A12"));

//        list.add(null);
//        list.add(null);
        list.addAll(list2);
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
        list.add(new User1("zhao2", 1, "000002", "万科A12"));
//        list.add(new User1("zhao2", 1, "000002", "万科A12"));
//        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//            System.out.println(i);
//        }
        List<User1> aa = list.stream().distinct().collect(Collectors.toList());
        System.out.println(aa.toString());
//        System.out.println(list);
//        System.out.println(aa);
        List<User1> listss=new ArrayList<>();
        listss.forEach(item->{
            System.out.println(item.getName());
        });


    }

    @Test
    public void stet() {
        String sourceindex = "1";
        String index = "2";
        String key = "oneling";

        key = "1".equals(sourceindex) ? "sdsd" : "找你哥哥";
        System.out.println(key);
        String key1 = "oneling";
        System.out.println(key.substring(1, 3));

    }

    @Test
    public void stringcovertlong() {
        String ss = "指令生成结束。";
        System.out.println(ss.length());
        String log = "9999";
        double sdsd = Double.valueOf(log);
        Long lll = Long.valueOf(log);
        System.out.println((long) sdsd);
        System.out.println(lll);

        BigDecimal l1 = new BigDecimal(2.0);
        BigDecimal l2 = new BigDecimal(3.0);
        int sd = l2.compareTo(l1);
        int sd1 = l1.compareTo(l2);
        System.out.println(sd);
        System.out.println(sd1);
        System.out.println(sd1 == 0);

        System.out.println(7 / 2);
        System.out.println(6 / 2);
        System.out.println(7 % 2);

    }

    @Test
    public void substring() {
        String str = "hueg001";
        int index1 = str.length();
        String index2 = str.substring(0, index1);
        String sdsd = str.substring(1);
        System.out.println(index2);
//        String ss = str.substring(0, index2);
    }

    @Test
    public void eor() {
        int a = 8 ^ 10;
        System.out.println(a);
        //整数转换成二进制数
        a = 100;
        er(a);
    }

    public void er(int tar) {
//        int a = tar / 2;
//       int temp =tar %2;
        //十进制转换成二进制
        int j = 0;
        String[] str = new String[10];
        while (tar != 0) {
            int temp = tar % 2;
            str[j] = String.valueOf(temp);
            j++;
            int a = tar / 2;
            tar = a;
        }

        //去处数组中的 null
        String[] newArray = Arrays.stream(str).filter(strrr -> strrr != null).toArray(String[]::new);

        //实现倒叙
        int left = 0;
        int right = newArray.length - 1;
        while (left <= right) {
            String temp = "";
            temp = newArray[left];
            newArray[left] = newArray[right];
            newArray[right] = temp;
            left++;
            right--;
        }

        String sds = "";
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != null) {
                sds += newArray[i];
            }
        }
        System.out.println(sds);
//        Stack stack = new Stack();   栈：先进后出 后进先出 实现倒叙

    }

    @Test
    public void reversetes() {
        String s = "1234";
        System.out.println(reverseTestSix(s));
        System.out.println("sdfsd");
    }

    //1.切割递归反转
    public static String reverseTestSix(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String aa = "sd";
        String ddd = s.substring(0, 1);
        String s1 = aa != null ? (reverseTestSix(s.substring(1)) + s.substring(0, 1)) : null;
        return s1;
    }


    //2.二分递归反转
    @Test
    public void testEr() {
        String s = "sdfks";
        String aa = reverseTeSeven(s);
        System.out.println(aa);
    }

    public String reverseTeSeven(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        String left = s.substring(0, length / 2);
        String right = s.substring(length / 2, length);
        String e = reverseTeSeven(right);
        String t = reverseTeSeven(left);
        String a = e + t;
        return a;
    }


    //异或算法
    @Test
    public void yihuoTest() {
        System.out.println(9 ^ 9);
        System.out.println(9 & 9);
        System.out.println(9 | 9);
    }

    //单项链表
    @Test
    public void node() {
        //拼接链表：
        Node headNode = new Node(0);
        Node current = headNode;
        int[] a = {1, 2, 3, 4, 5};
        for (int i = 0; i < 5; i++) {
            Node temp = new Node(a[i]);
            current.setNext(temp);//当前节点是指向2 添加新的接点(next)3
            current = temp;       //把这个节点地址从指向2 变成指向3
        }
        //单链表 双链表
        //1.单链表
        //2.把给定值删除
        Node pre=null;
        Node next =null;
        while(headNode!=null){
            next=headNode.getNext(); //第一个几点
            headNode.setNext(pre); //pre 第二个节点 set第一个节点
            pre=headNode;  //   得到倒叙head 的节点  存入  pre
            headNode=next; //初始head 节点
        }
        System.out.println(pre.toString());

    }

    //双向链表 双链表如何反转
    @Test
    public void doubleNodete(){
        //拼接链表：
        DoubleNode headNode = new DoubleNode(0);
        DoubleNode current = headNode;
        int[] a = {1, 2, 3, 4, 5};
        for (int i = 0; i < 5; i++) {
            DoubleNode temp = new DoubleNode(a[i]);
            current.setNext(temp);//当前节点是指向2 添加新的接点(next)3
            current = temp;       //把这个节点地址从指向2 变成指向3
        }

        DoubleNode pre=null;
        DoubleNode next=null ;
        while (headNode!=null){
             next=headNode.getNext();

             headNode.setNext(pre);
             headNode.setPre(next);
             pre=headNode;

             headNode=next;
        }
        System.out.println(headNode.toString());

    }



}

