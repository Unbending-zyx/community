package com.student.community.cache;

import com.student.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> getTags(){
        List<TagDTO> tagDTOS=new ArrayList<>();
        TagDTO tagDTO1=new TagDTO();
        tagDTO1.setCategoryName("交友");
        tagDTO1.setTags(Arrays.asList("找小哥哥","找小姐姐","表白墙","考研伙伴","游戏开黑","日常锻炼","学习互助"));
        tagDTOS.add(tagDTO1);

        TagDTO tagDTO2=new TagDTO();
        tagDTO2.setCategoryName("学习");
        tagDTO2.setTags(Arrays.asList("自习室","问题求解","计算机技术","四六级","考证","学习用品"));
        tagDTOS.add(tagDTO2);

        TagDTO tagDTO3=new TagDTO();
        tagDTO3.setCategoryName("失物招领");
        tagDTO3.setTags(Arrays.asList("寻物品","寻失主"));
        tagDTOS.add(tagDTO3);

        TagDTO tagDTO4=new TagDTO();
        tagDTO4.setCategoryName("物品出售");
        tagDTO4.setTags(Arrays.asList("求购","出售"));
        tagDTOS.add(tagDTO4);
        return tagDTOS;
    }

    public static String filterInvalid(String inTags){
        String[] inTagArr = StringUtils.split(inTags, ",");

        List<TagDTO> tagDTOS = getTags();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(inTagArr).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;

    }
}
