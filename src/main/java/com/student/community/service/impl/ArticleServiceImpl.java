package com.student.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.student.community.dao.IArticleDAO;
import com.student.community.dto.ArticleDTO;
import com.student.community.service.IArticleService;
import com.student.community.utils.ArticleUtil;
import com.student.community.vo.Article;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDAO articleDAO;
    @Autowired
    private ArticleUtil articleUtil;

    @Override
    public int insertArticle(Article article) {
        return articleDAO.insertArticle(article);
    }

    @Override
    public List<Article> selectAllArticle() {
        return articleDAO.selectAllArticle();
    }

    @Override
    public int selectArticleCount() {
        return articleDAO.selectArticleCount();
    }

    @Override
    public List<ArticleDTO> selectAllArticleDTO() {
        return articleDAO.selectAllArticleDTO();
    }

    @Override
    public int selectArticleCountByUserId(int creatorId) {
        return articleDAO.selectArticleCountByUserId(creatorId);
    }

    @Override
    public List<ArticleDTO> selectAllArticleDTOByUserId(User user) {
        return articleDAO.selectAllArticleDTOByUserId(user);
    }

    @Override
    public ArticleDTO selectArticleDTOById(Integer id) {
        return articleDAO.selectArticleDTOById(id);
    }

    @Override
    public Article selectArticleById(Integer id) {
        return articleDAO.selectArticleById(id);
    }

    @Override
    public int updateArticleById(Article article) {
        return articleDAO.updateArticleById(article);
    }

    @Override
    public int updateArticleReadingCountById(int id) {
        return articleDAO.updateArticleReadingCountById(id);
    }

    @Override
    public int updateArticleCommentCountById(int id) {
        return articleDAO.updateArticleCommentCountById(id);
    }

    //处理tag  使之变为正则可用的格式
    @Override
    public List<Article> selectArticleByTags(int id, String tag) {
        Article article=new Article();
        String newTag=buileString(tag,",");
        article.setId(id);
        article.setTag(newTag);
        return articleDAO.selectArticleByTags(article);
    }

    @Override
    public Map<String, Object> selectArticleDTOByTitleLike(String selectArticleText, int pageNum, int pageSize) {
        Map<String,Object> result=new HashMap<>();
        String newSelectArticleText=buileString(selectArticleText," ");
        int pageCount=articleDAO.selectArticleCountByTitleLike(newSelectArticleText);
        pageCount=articleUtil.buildPageCount(pageCount,pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleDTOList=articleDAO.selectArticleDTOByTitleLike(newSelectArticleText);
        if (articleDTOList!=null && articleDTOList.size()>0 && pageCount>0){
            result.put("pageCount", pageCount);
            result.put("pageNum", pageNum);
            result.put("code", 200);
            result.put("articleListQuary",articleDTOList);
        }
        return result;
    }

    private String buileString(String selectArticleText,String regex) {
        String[] text=selectArticleText.split(regex);
        String newText="";
        for (int i=0;i<text.length;i++){
            if (i==text.length-1){
                newText=newText+text[i];
            }else{
                newText=newText+text[i]+"|";
            }
        }
        return newText;
    }


}
