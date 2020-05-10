
import javax.media.j3d.*;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Box;

public class Cubie {
    Box kubik;
    TransformGroup przesuniecie;
    TransformGroup rotacja;


    private  Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
    private  Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
    private  Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
    private  Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
    private  Color3f Orange = new Color3f(1.0f, 0.4f, 0.0f);
    private  Color3f White = new Color3f(1.0f, 1.0f, 1.0f);
    private  Color3f Black = new Color3f(0.1f, 0.1f, 0.1f);


    private  int[] faces = { Box.TOP, Box.FRONT, Box.RIGHT,
            Box.BACK, Box.LEFT, Box.BOTTOM };

    private Appearance[] app =
            {getAppearance(0), getAppearance(1),
                    getAppearance(2), getAppearance(3),
                    getAppearance(4),getAppearance(5)};


    //Konstruktor ktory tworzy kostkę i koloruje sciany
    public Cubie (float size, float x, float y, float z)
    {

        kubik = new Box(size, size, size,Box.GENERATE_NORMALS, null);

        for (int i = 0; i < faces.length; i++) {
            Shape3D shape = kubik.getShape(faces[i]);
            shape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
            shape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
            shape.setAppearance(app[i]);


        }

        //transformacja przesuwająca kostki na swoje miejsca


        double zmienna=270;


        Transform3D transform = new Transform3D();
        Transform3D tf = new Transform3D();
        Vector3d vector = new Vector3d( x, y, z);
        transform.setTranslation(vector);



        przesuniecie = new TransformGroup(transform);
        przesuniecie.addChild(kubik);

        rotacja = new TransformGroup(tf);

        rotacja.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacja.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacja.addChild(przesuniecie);



    }
    //gettery
    public TransformGroup getTg()
    {
        return rotacja;
    }
    // nasza zmienna transformacja
    public Transform3D getTransform()
    {
        Transform3D temporary = new Transform3D();
         rotacja.getTransform(temporary);
         return temporary;
    }
    public Appearance getAppearance(int i)
    {


        Appearance apptest = new Appearance();
        Color3f kol = ktoryKolor(i);
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        apptest.setColoringAttributes(att);

        return apptest;



    }

    public Box getCubie()
    {
        return kubik;
    }


    // wybor koloru bo sciany pudelka sa intami
    public Color3f ktoryKolor(int x)
    {
        switch(x)
        {
            case 0: return Red;
            case 1: return Green;
            case 2: return Blue;
            case 3: return Yellow;
            case 4: return Orange;
            case 5: return White;

        }
        return Black;
    }



}
