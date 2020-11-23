/*
 * Copyright 2018 kiwipeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.bean;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  使用@Value注解注入,1.测试器对hibernate validator是否支持；2.对SpEL是否支持；3.对复杂封装类型是否支持
 *
 * @author Wujun
 * @create 2018/07/01
 */
@Component
//@Validated
public class MiddleStudent {
    @Value("${kiwipeach.id}")
    private Integer id;
    @Email
    //@Value("${kiwipeach.name}")//不支持hibernate validator校验
    private String name;
    @Value("#{${kiwipeach.weight}*2}")//支持SpEL,注意是以 '#'开头
    private Double weight;
    @Value("${kiwipeach.birthday}")

    /*以下符合类型@Value注解都不能进行注入*/
    private Date birthday;
    private Pet pet;                        //宠物
    private List<String> tags;              //个性标签
    private List<Hobby> hobbies;            //兴趣爱好
    private Map<String,Address> addressMap; //收货地址信息
    private Map<String,String> scores; //收货地址信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, Address> getAddressMap() {
        return addressMap;
    }

    public void setAddressMap(Map<String, Address> addressMap) {
        this.addressMap = addressMap;
    }

    public Map<String, String> getScores() {
        return scores;
    }

    public void setScores(Map<String, String> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "MiddleStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", birthday=" + birthday +
                ", pet=" + pet +
                ", tags=" + tags +
                ", hobbies=" + hobbies +
                ", addressMap=" + addressMap +
                ", scores=" + scores +
                '}';
    }
}
