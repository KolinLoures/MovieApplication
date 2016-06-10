package com.example.kolin.movieapplication.data.dataModule;

import com.example.kolin.movieapplication.data.Repository;
import com.example.kolin.movieapplication.domain.IRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by n.kirilov on 10.06.2016.
 */

@Module
public class RepositoryModel {

    //На самом деле класс должен называться RepositoryModul,
    //который обеспечивает для инъекции в другом классе обеъект класса,
    //реализующего интерфейс IRepository

    @Singleton
    @Provides
    public IRepository providesRepository(){
        return new Repository();
    }
}
