package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Souris {

    
    int coordX;
    int coordY;
    Texture img2;
    int longueureffective;
    int hauteureffective;
    int longueurFenetre;
    int hauteurFenetre;
    Texture img;
    final int vitesse = 5;
    Rectangle rekt;
    Boolean clickgauche;
    int NBshoot = 2;

   public Souris() {
    
    img2 = new Texture("viseur.png");
    //coordX = 0;
    //coordX = 0;
    longueureffective = img2.getWidth();
    hauteureffective = img2.getHeight();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();
    rekt = new Rectangle(coordX, coordY, longueureffective, hauteureffective);
    
   }

	public void maJEtat() {
        Suivre_souris();
        forcerAresterDansCadre();
	}

    private void Suivre_souris() {

        coordX = Gdx.input.getX()-25;  // donne les emplacements en X et Y de la souris 
        coordY = Gdx.graphics.getHeight() - Gdx.input.getY()-25;
     }

    private  void forcerAresterDansCadre() {
         // Gestion bordure droite
      if (coordX + longueureffective > longueurFenetre) {
        coordX = longueurFenetre - longueureffective;
      }

      // Gestion bordure gauche
      if (coordX < 0) {
        coordX = 0;
      }

      // Gestion bordures haute
      if (coordY + hauteureffective > hauteurFenetre) {
        coordY = hauteurFenetre - hauteureffective;
      }

      // Gestion bordure basse
      if (coordY < 0) {
        coordY = 0;
      }

    rekt.setPosition(coordX, coordY); 
  }
    

  public void dessiner(SpriteBatch batch) {   //Les variables local sont supprimés dés que l'on sort de la méthode
                                                // ici c'est la copie de la rérérence a l'objet batch qui disparait mais la réference continue a éxister dans la class principal 
        batch.draw(img2,coordX, coordY);
      }

	public boolean estEnCollisionAvec(ArrayList<Sprite> sprites) {

        // Pour chaque sprite dans srpites 
        //si sprite touche le joueurs
        // alors renvoyer vraie
        // sinon faux 
        // boucle for reach = Sprite = le type de tableaux, sprites c'est le nom du tableau que l'on va parcourrir, sprite est une variable
        for (Sprite sprite : sprites){
            if (estEnCollisionAvec(sprite)){
               return true;
              } 
          }
           return false;
        }
    private boolean estEnCollisionAvec(Sprite sprite){
        if (rekt.overlaps(sprite.rekt)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean clicGauche() {
        if (entreeClicGauche()) {
            return true;
        }
        return false;
    }

    private boolean entreeClicGauche() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            return true;
        }
        return false;
    }

    public boolean clicGauchetir(){
      if (entrerclicGauchetir()){
        NBshoot = NBshoot -1;
        System.out.println("Il vous reste " + NBshoot + " balles");
        return true;
      }
        return false;
    }

    private boolean entrerclicGauchetir(){
      if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
        return true;
    }
        return false;
    }
}

    



