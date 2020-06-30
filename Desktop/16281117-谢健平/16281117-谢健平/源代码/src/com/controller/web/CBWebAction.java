package com.controller.web;

import com.entity.Article;
import com.entity.Click;
import com.entity.Comment;
import com.service.ArticleService;
import com.service.ClickService;
import com.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class CBWebAction extends BaseWebAction {
    // 注入AdminService 并getter setter
    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    @Resource
    private ClickService clickService;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        DecimalFormat df = new DecimalFormat("######0.0000");
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {
            {
                add("n");
                add("nt");
                add("nz");
                add("nw");
                add("nl");
                add("ns");
                add("ng");
            }
        };

        //从本地读取采集好的语料库，每篇文档占用一行
        FileInputStream sampleFile = new FileInputStream("C:\\Users\\Administrator\\Desktop\\news.txt");
        InputStreamReader isr = new InputStreamReader(sampleFile, "UTF-8");
        String text = "";
        BufferedReader buread = new BufferedReader(isr);
        List<List<Map.Entry<String, Double>>> Textlist = new ArrayList<>();
        try {
            while ((text = buread.readLine()) != null) {
                //正则匹配去除文本中的标点符号
                text = text.replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
                //采用nlp分词
                Result result = NlpAnalysis.parse(text);
                List<Term> terms = result.getTerms();
                //size用来统计每篇文档分词后的总词数
                double size = 0.0;
                Map<String, Double> dict = new HashMap<>();
                for (int i = 0; i < terms.size(); i++) {
                    String key = terms.get(i).getName();
                    String natureStr = terms.get(i).getNatureStr(); //拿到词性
                    if (expectedNature.contains(natureStr)) {
                        size++;
                        if (dict.containsKey(key)) {
                            double count = dict.get(key) + 1;
                            dict.put(key, count);
                        } else {
                            dict.put(key, 1.0);
                        }
                    }
                }
                List<Map.Entry<String, Double>> list = new ArrayList<>();
                for (Map.Entry<String, Double> entry : dict.entrySet()) {
                    Double tf = entry.getValue() / size;
                    entry.setValue(tf);
                    list.add(entry); //将map中的元素放入list中
                }
                Textlist.add(list);
                System.out.println(list.size());
            }
            int index = 0;
            for (List<Map.Entry<String, Double>> entry : Textlist) {
                index ++;
                for (Map.Entry<String, Double> each : entry) {
                    String key = each.getKey();
                    Double idf = getIDF(key, Textlist);
                    Double tf_idf = each.getValue() * idf;
                    each.setValue(Double.valueOf(df.format(tf_idf)));
                }
                entry.sort(new Comparator<Map.Entry<String, Double>>() {
                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        Double d1 = o1.getValue();
                        Double d2 = o2.getValue();
                        return d2.compareTo(d1);
                    }
                    //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
                });
                System.out.println("处理了"+index+"篇文档");
            }
            saveTextTF(Textlist, "E:\\DataMiningLearn\\TextTFIDF.txt");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //计算IDF值
    public static double getIDF(String key, List<List<Map.Entry<String, Double>>> Textlist) {
        try {
            Double count = 0.0;
            for (List<Map.Entry<String, Double>> entry : Textlist) {
                for (Map.Entry<String, Double> each : entry) {
                    if (key.equals(each.getKey())) {
                        count++;
                        break;
                    }
                }
            }
            Double idf = Math.log(Textlist.size() / (count + 1));
            return idf;
        } catch (Exception e) {
            System.out.println(e.toString());
            return 0.0;
        }
    }

    public static void saveTextTF(List<List<Map.Entry<String, Double>>> list, String path) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("保存TF-IDF值");
        int countLine = 0;
        File outPutFile = new File(path);
        //if file exists, then delete it
        if (outPutFile.exists()) {
            outPutFile.delete();
        }
        //if file doesnt exists, then create it
        if (!outPutFile.exists()) {
            outPutFile.createNewFile();
        }
        FileWriter outPutFileWriter = new FileWriter(outPutFile);
        for (List<Map.Entry<String, Double>> entry : list) {
            for (Map.Entry<String, Double> each : entry) {
                outPutFileWriter.write(each.getKey() + ":" + each.getValue() + ",");
            }
            outPutFileWriter.write("\n");
        }
        outPutFileWriter.close();
        System.out.println("属性词个数：" + countLine);
    }
    public String article(HttpServletRequest request, Map<String, Object> map) {
        this.front();
        Map<String, Object> parameter = new HashMap<String, Object>();
        String catalogid = request.getParameter("catalogid");//搜索关键词
        String bookinfoid = request.getParameter("bookinfoid");//搜索关键词
        String keyword = request.getParameter("keyword");//搜索关键词

        if (catalogid != null && catalogid != "") parameter.put("catalogid", catalogid);//搜索参数
        if (bookinfoid != null && bookinfoid != "") parameter.put("bookinfoid", bookinfoid);//搜索参数
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        int rowCountTotal = articleService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Article> articleList = articleService.getAll(parameter);


        //设定页面参数,传递给JSP页面
        Map<String, Object> pager = new HashMap<String, Object>();
        int pageCount = 1;// 总页数
        // 计算总页数
        if (rowCountTotal % pageSize == 0) {
            pageCount = rowCountTotal / pageSize;
        } else {
            pageCount = rowCountTotal / pageSize + 1;
        }
        pager.put("pageNumber", pageNumber);//当前页
        pager.put("pageCount", pageCount);//总页数
        pager.put("rowCountTotal", rowCountTotal);//记录总条数

        map.put("keyword", keyword);
        map.put("catalogid", catalogid);
        map.put("bookinfoid", bookinfoid);
        map.put("pager", pager);
        map.put("articleList", articleList);
        return "Web/book";//跳转到WebContent目录下对应的JSP页面
    }


}