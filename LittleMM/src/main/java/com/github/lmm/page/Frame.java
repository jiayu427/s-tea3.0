package com.github.lmm.page;

import com.github.lmm.annotation.Commit;
import com.github.lmm.annotation.FrameLocator;
import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午2:09
 *
 */
public abstract class Frame{
    private Logger logger = Logger.getLogger(Frame.class);
    private WebDriver currentFrame;
    private String commit;
    private ICurrentPage page;
    private ElementManager elementManager;
    public Frame(Frame frame,By by){
        this.currentFrame=frame.getCurrentFrame().switchTo().frame(page.getCurrentWindow().findElement(by));
        this.page=frame.getPage();
        this.elementManager=frame.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }
    public Frame(Frame frame,By by,int index){
        this.currentFrame=frame.getCurrentFrame().switchTo().frame(page.getCurrentWindow().findElements(by).get(index));
        this.page=frame.getPage();
        this.elementManager=frame.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }

    public Frame(Frame frame,Integer frameindex){
        this.currentFrame=frame.getCurrentFrame().switchTo().frame(frameindex);
        this.page=frame.getPage();
        this.elementManager=frame.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }

    public Frame(Frame frame,String nameorId){

        this.currentFrame=frame.getCurrentFrame().switchTo().frame(nameorId);
        this.page=frame.getPage();
        this.elementManager=frame.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }
    public Frame(Frame frame,TempElement element){
        this(frame,element.getLocator(),element.getIndex());
    }
    public Frame(ICurrentPage page,By by){
        this.page=page;
        this.currentFrame=page.getCurrentWindow().switchTo().frame(page.getCurrentWindow().findElement(by));
        this.elementManager=page.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }
    public Frame(ICurrentPage page,By by,int index){
        this.page=page;
        this.currentFrame=page.getCurrentWindow().switchTo().frame(page.getCurrentWindow().findElements(by).get(index));
        this.elementManager=page.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }

    public Frame(ICurrentPage  page,Integer frameindex){
        this.page=page;
        this.currentFrame=page.getCurrentWindow().switchTo().frame(frameindex);
        this.elementManager=page.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }

    }

    public Frame(ICurrentPage page,String nameorId){
        this.page=page;
        this.currentFrame=page.getCurrentWindow().switchTo().frame(nameorId);
        this.elementManager=page.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }
    }
    public Frame(ICurrentPage page,TempElement element){
        this(page,element.getLocator(),element.getIndex());
    }
    public Frame(ICurrentPage page){
        this.page=page;
        if(getFrameIndex()!=-1){
            this.currentFrame=page.getCurrentWindow().switchTo().frame(getFrameIndex());
        }else if(getNameOrId()!=null){
            this.currentFrame=page.getCurrentWindow().switchTo().frame(getNameOrId());
        }else{
            this.currentFrame=page.getCurrentWindow().switchTo().frame(page.getCurrentWindow().findElements(getLocator()).get(getIndex()));
        }
        this.elementManager=page.getElementManager();
        if(this.getClass().isAnnotationPresent(Commit.class)){
            this.commit=this.getClass().getAnnotation(Commit.class).value();
        }

    }

    public ElementManager getElementManager(){
        return this.elementManager;
    }

    public WebDriver getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(WebDriver currentFrame) {
        this.currentFrame = currentFrame;
    }

    public ICurrentPage getPage() {
        return page;
    }

    public void setPage(SourcePage page) {
        this.page = page;
    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public <T>T frame(Class<T>clazz){
        Object frame=null;
        try {
            Constructor<T> constructor=clazz.getConstructor(Frame.class);
            frame=constructor.newInstance(this);
        } catch (NoSuchMethodException e) {
            throw new  NoSuchMethodError("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (InstantiationException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有找到这个class类"+clazz.getName()+",请检查类是否被加载或者类名是否正确");
        }
        return (T)frame;
    }

    protected By getLocator(){
        if(this.getClass().isAnnotationPresent(FrameLocator.class)){
            FrameLocator frameLocator=this.getClass().getAnnotation(FrameLocator.class);
            Locator locator = frameLocator.locator();
            String value=frameLocator.value();
            //int index = frameLocator.index();
            return  locator.getLocator(value);
        }else{
            logger.warn("这个Frame没有启用注解来标注Frame的位置。");
            return null;
        }
    }

    protected int getIndex(){
        if(this.getClass().isAnnotationPresent(FrameLocator.class)){
            FrameLocator frameLocator=this.getClass().getAnnotation(FrameLocator.class);
            int index =frameLocator.index();
            return index;
        } else{
            logger.warn("这个Frame没有启用注解来标注Frame的位置。");
            return 0;
        }
    }

    protected String getNameOrId(){
        if(this.getClass().isAnnotationPresent(FrameLocator.class)){
            FrameLocator frameLocator=this.getClass().getAnnotation(FrameLocator.class);
            String nameOrId = frameLocator.nameOrId();
            return nameOrId;
        } else{
            logger.warn("这个Frame没有启用注解来标注Frame的位置。");
            return null;
        }
    }

    protected  int getFrameIndex(){
        if(this.getClass().isAnnotationPresent(FrameLocator.class)){
            FrameLocator frameLocator=this.getClass().getAnnotation(FrameLocator.class);
            int frameindex = frameLocator.frameIndex();
            return frameindex;
        }else{
            logger.warn("这个Frame没有启动注解来标注Frame的位置。");
            return -1;
        }
    }

    public IElement element(String id){
        TempElement tempElement=this.elementManager.getTempElement(id);
        return new FrameElement(this,tempElement);
    }

    public IElement element(){
        return new FrameElement(this);
    }

    public IElement element(By by){
        FrameElement frameElement=new FrameElement(this);
        frameElement.addLocator(by);
        return frameElement;
    }

    public IElement element(By by,int index){
        FrameElement frameElement=new FrameElement(this);
        frameElement.addLocator(by,index);
        return frameElement;
    }
    
    public Table table() {
        return new Table(this.getPage().getBrowser());
    }

    
    public Table table(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        return new Table(this.getPage().getBrowser(),tempElement);
    }

    
    public Table table(By by) {
        Table table = new Table(this.getPage().getBrowser());
        table.addLocator(by);
        return table;
    }

    
    public Table table(By by, Integer index) {
        Table table = new Table(this.getPage().getBrowser());
        table.addLocator(by,index);
        return table;
    }

    
    public Button button() {
        Button button=new Button(this.getPage().getBrowser());
        return button;
    }

    
    public Button button(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Button button=new Button(this.getPage().getBrowser(),tempElement);
        return button;
    }

    
    public Button button(By by) {
        Button button=new Button(this.getPage().getBrowser());
        button.addLocator(by);
        return button;
    }

    
    public Button button(By by, Integer index) {
        Button button=new Button(this.getPage().getBrowser());
        button.addLocator(by,index);
        return button;
    }

    
    public CheckBox checkBox() {
        return new CheckBox(this.getPage().getBrowser());
    }

    
    public CheckBox checkBox(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        CheckBox checkBox=new CheckBox(this.getPage().getBrowser(),tempElement);
        return checkBox;
    }

    
    public CheckBox checkBox(By by) {
        CheckBox checkBox=new CheckBox(this.getPage().getBrowser());
        checkBox.addLocator(by);
        return checkBox;
    }

    
    public CheckBox checkBox(By by, Integer index) {
        CheckBox checkBox=new CheckBox(this.getPage().getBrowser());
        checkBox.addLocator(by,index);
        return checkBox;
    }

    
    public Form form() {
        return new Form(this.getPage().getBrowser());
    }

    
    public Form form(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Form form=new Form(this.getPage().getBrowser(),tempElement);
        return form;
    }

    
    public Form form(By by) {
        Form form=new Form(this.getPage().getBrowser());
        form.addLocator(by);
        return form;
    }

    
    public Form form(By by, Integer index) {
        Form form=new Form(this.getPage().getBrowser());
        form.addLocator(by,index);
        return form;
    }

    
    public Image image() {
        return new Image(this.getPage().getBrowser());
    }

    
    public Image image(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Image image=new Image(this.getPage().getBrowser(),tempElement);
        return image;
    }

    
    public Image image(By by) {
        Image image=new Image(this.getPage().getBrowser());
        image.addLocator(by);
        return image;
    }

    
    public Image image(By by, Integer index) {
        Image image=new Image(this.getPage().getBrowser());
        image.addLocator(by, index);
        return image;
    }

    
    public Link link() {
        Link link = new Link(this.getPage().getBrowser());
        return link;
    }

    
    public Link link(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Link link=new Link(this.getPage().getBrowser(),tempElement);
        return link;
    }

    
    public Link link(By by) {
        Link link=new Link(this.getPage().getBrowser());
        link.addLocator(by);
        return link;
    }

    
    public Link link(By by, Integer index) {
        Link link=new Link(this.getPage().getBrowser());
        link.addLocator(by, index);
        return link;
    }

    
    public RadioButton radioButton() {
        return new RadioButton(this.getPage().getBrowser());
    }

    
    public RadioButton radioButton(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        RadioButton radioButton=new RadioButton(this.getPage().getBrowser(),tempElement);
        return radioButton;
    }

    
    public RadioButton radioButton(By by) {
        RadioButton radioButton=new RadioButton(this.getPage().getBrowser());
        radioButton.addLocator(by);
        return radioButton;
    }

    
    public RadioButton radioButton(By by, Integer index) {
        RadioButton radioButton=new RadioButton(this.getPage().getBrowser());
        radioButton.addLocator(by);
        return radioButton;
    }

    
    public RichTextField richTextField() {
        return new RichTextField(this.getPage().getBrowser());
    }

    
    public RichTextField richTextField(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        RichTextField richTextField=new RichTextField(this.getPage().getBrowser(),tempElement);
        return richTextField;
    }

    
    public RichTextField richTextField(By by) {
        RichTextField richTextField=new RichTextField(this.getPage().getBrowser());
        richTextField.addLocator(by);
        return richTextField;
    }

    
    public RichTextField richTextField(By by, Integer index) {
        RichTextField richTextField=new RichTextField(this.getPage().getBrowser());
        richTextField.addLocator(by, index);
        return richTextField;
    }

    
    public Select select() {
        return new Select(this.getPage().getBrowser());
    }

    
    public Select select(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        Select select=new Select(this.getPage().getBrowser(),tempElement);
        return select;
    }

    
    public Select select(By by) {
        Select select = new Select(this.getPage().getBrowser());
        select.addLocator(by);
        return select;
    }

    
    public Select select(By by, Integer index) {
        Select select=new Select(this.getPage().getBrowser());
        select.addLocator(by, index);
        return select;
    }

    
    public TextField textField() {
        return new TextField(this.getPage().getBrowser());
    }

    
    public TextField textField(String id) {
        TempElement tempElement=this.elementManager.getTempElement(id);
        TextField textField= new TextField(this.getPage().getBrowser(),tempElement);
        return textField;
    }

    
    public TextField textField(By by) {
        TextField textField=new TextField(this.getPage().getBrowser());
        textField.addLocator(by);
        return textField;
    }

    
    public TextField textField(By by, Integer index) {
        TextField textField=new TextField(this.getPage().getBrowser());
        textField().addLocator(by,index);
        return textField;
    }

    
    public <T> T selfConfigElement(T clazz) {
        return clazz;
    }


    public Frame frame(int index) {
        return new DefaultFrame(this,index);
    }


    public Frame frame(String nameOrId) {
        return new DefaultFrame(this,nameOrId);
    }


    public Frame frame(By by) {
        return new DefaultFrame(this,by);
    }


    public Frame frame(By by, int index) {
        return new DefaultFrame(this,by,index);
    }

}