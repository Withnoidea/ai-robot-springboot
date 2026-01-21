package com.quanxiaoha.ai.robot.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @Author: withnoidea
 * @Date: 2026/01/20
 * @Version: v1.0.0
 * @Description: 演员 - 电影集合
 **/
@JsonPropertyOrder({"actor", "movies"})
public record ActorFilmography(String actor, List<String> movies) {
}
