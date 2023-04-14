package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();

        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String dimension = image.getDimensions();

        String[] imgDimension = dimension.split("x");

        String[] screenDimension = screenDimensions.split("x");

        int verticalCount = Integer.parseInt(screenDimension[0]) / Integer.parseInt(imgDimension[0]);
        int horizontalCount = Integer.parseInt(screenDimension[1]) / Integer.parseInt(imgDimension[1]);

        return verticalCount * horizontalCount; //no of  maximum images to fit in given screen dimension
    }
}
