package com.controller.web;

import com.entity.Article;
import com.entity.Click;
import com.entity.Comment;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.service.ArticleService;
import com.service.ClickService;
import com.service.CommentService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/web")
public class UserCFWebAction extends BaseWebAction {


    // 注入AdminService 并getter setter
    @Resource
    private ArticleService articleService1;

    @Resource
    private CommentService commentService;

    @Resource
    private ClickService clickService;



    @RequestMapping("article.action")
    public String article(HttpServletRequest request, Map<String, Object> map) {
        this.front();
        Map<String, Object> parameter = new HashMap<String, Object>();
        String catalogid = request.getParameter("catalogid");//搜索关键词
        String bookinfoid = request.getParameter("bookinfoid");//搜索关键词
        String keyword = request.getParameter("keyword");//搜索关键词

        if (catalogid != null && catalogid != "") parameter.put("catalogid", catalogid);//搜索参数
        if (bookinfoid != null && bookinfoid != "") parameter.put("bookinfoid", bookinfoid);//搜索参数
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        int rowCountTotal = articleService1.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Article> articleList = articleService1.getAll(parameter);


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

    // 阅读
    @RequestMapping("articleDetail.action")
    public String articleDetail(HttpServletRequest request,String id, Map<String, Object> map) throws TasteException {
        this.front();
        Article article = articleService1.getById(id);
        article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
        articleService1.update(article);
        map.put("article", article);



        if (getRequest().getSession().getAttribute("userid") == null) {

            //如果登录插入最近浏览记录
            String userid = (String) this.getSession().getAttribute("userid");
            Click click = new Click();
            click.setAddtime(new Date());
            click.setArticleid(id);
            click.setMemo(this.getRequest().getParameter("memo"));
            click.setUsersid(userid);
            clickService.insert(click);
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPassword("root");
            dataSource.setUser("root");
            dataSource.setDatabaseName("ssm_book1");
            dataSource.setURL("jdbc:mysql://localhost:3308/ssm_book1?useUnicode=true&amp;characterEncoding=UTF-8");
            JDBCDataModel dataModel=new MySQLJDBCDataModel(dataSource,"preference","userId","bookId","val", "time");
            //	JDBCDataModel dataModel=new MySQLJDBCDataModel(dataSource,"mytable01","uid","iid","val",null);
            // 定义数据模型
            DataModel model=dataModel;
            //2）定义相识度
            UserSimilarity similarity=new PearsonCorrelationSimilarity(model);
            //3）定义最近邻域
            UserNeighborhood neighborhood=new NearestNUserNeighborhood(3,similarity,model);
            //定义推荐器
            Recommender r=new GenericUserBasedRecommender(model,neighborhood,similarity);
            LongPrimitiveIterator iter =model.getUserIDs();


        }
        //---------------------------------------------获取评论
        Map<String, Object> parameter = new HashMap<String, Object>();


        String keyword = request.getParameter("keyword");//搜索关键词
        if (keyword != null && keyword != "") parameter.put("keyword", keyword);//搜索参数


        parameter.put("articleid", article.getArticleid());//搜索参数


        int rowCountTotal = commentService.getRowSize(parameter);//获取总数
        int pageSize = 10;// 分页大小
        int pageNumber = 1; //设定页面参数,传递给JSP页面

        if (request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "") {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//当前页
        }
        parameter.put("limit", pageSize);//搜索参数
        parameter.put("start", (pageNumber - 1) * pageSize);//搜索参数
        List<Comment> commentList = commentService.getAll(parameter);





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
        map.put("pager", pager);

        map.put("commentList", commentList);

        return "Web/bookContent";//跳转到WebContent目录下对应的JSP页面
    }


}