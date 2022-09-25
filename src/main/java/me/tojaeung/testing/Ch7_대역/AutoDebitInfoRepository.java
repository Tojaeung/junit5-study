package me.tojaeung.testing.Ch7_대역;

public interface AutoDebitInfoRepository {

    
    void save(AutoDebitInfo info);

    AutoDebitInfo findOne(String userId);

}
