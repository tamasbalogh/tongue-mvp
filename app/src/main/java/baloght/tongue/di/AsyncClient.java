package baloght.tongue.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Balogh Tamas on 2018. 04. 02..
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AsyncClient {
}
