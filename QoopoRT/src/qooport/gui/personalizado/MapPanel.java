package qooport.gui.personalizado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class MapPanel extends JPanel {

    private JLabel lblValProvedor;
    private JLabel lblVallongitude;
    private JLabel lblVallatitude;
    private JLabel lblValaltitude;
    private JLabel lblValvitesse;
    private JLabel lblValprecision;
    private JLabel lblVallastdata;
    private JMapViewer mapViewer;
    private double lastLongitude;
    private double lastLatitude;

    /**
     * Create the panel.
     */
    public MapPanel(boolean global) {
        try {
            JSplitPane splitPane = new JSplitPane();
            GroupLayout groupLayout;

            mapViewer = new JMapViewer();

            if (!global) {
                groupLayout = new GroupLayout(this);
                groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 545, Short.MAX_VALUE)
                                        .addContainerGap())
                );
            } else {
                //groupLayout= new BorderLayout();
                groupLayout = new GroupLayout(this);
                groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(mapViewer, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(mapViewer, GroupLayout.PREFERRED_SIZE, 545, Short.MAX_VALUE)
                                        .addContainerGap())
                );
            }

            if (!global) {
                splitPane.setLeftComponent(mapViewer);
            }

            JPanel panel = new JPanel();
            if (!global) {
                splitPane.setRightComponent(panel);
            }

            JPanel panel_1 = new JPanel();
            panel_1.setBorder(new TitledBorder(null, "Informacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            JButton btnCenterView = new JButton("Vista central"); //$NON-NLS-1$
            btnCenterView.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    centerMapView();
                }
            });
//            JPanel panel_2 = new JPanel();
//            panel_2.setBorder(new TitledBorder(null, "Grupo de inicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            GroupLayout gl_panel = new GroupLayout(panel);
            gl_panel.setHorizontalGroup(
                    gl_panel.createParallelGroup(Alignment.TRAILING)
                            .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                            .addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            //                                    .addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            .addComponent(btnCenterView, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            gl_panel.setVerticalGroup(
                    gl_panel.createParallelGroup(Alignment.LEADING)
                            .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    //                            .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(btnCenterView)
                                    .addGap(161))
            );

            JLabel lblProvedor = new JLabel("Proveedor:"); //$NON-NLS-1$
            lblValProvedor = new JLabel("");
            JLabel lblLongitude = new JLabel("Longitud:"); //$NON-NLS-1$
            lblVallongitude = new JLabel("");
            JLabel lblLatitude = new JLabel("Latitud:"); //$NON-NLS-1$
            lblVallatitude = new JLabel("");
            JLabel lblAltitude = new JLabel("Altitud:"); //$NON-NLS-1$
            lblValaltitude = new JLabel("");
            JLabel lblVitesse = new JLabel("Velocidad:"); //$NON-NLS-1$
            lblValvitesse = new JLabel("");
            JLabel lblPrcision = new JLabel("Precición:"); //$NON-NLS-1$
            lblValprecision = new JLabel("");
            JLabel lblLastData = new JLabel("Fecha de ubicación:"); //$NON-NLS-1$
            lblVallastdata = new JLabel("");
            GroupLayout gl_panel_1 = new GroupLayout(panel_1);
            gl_panel_1.setHorizontalGroup(
                    gl_panel_1.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panel_1.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                            .addGroup(gl_panel_1.createSequentialGroup()
                                                    .addComponent(lblProvedor)
                                                    .addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(lblValProvedor))
                                            .addGroup(gl_panel_1.createSequentialGroup()
                                                    .addComponent(lblLongitude)
                                                    .addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(lblVallongitude))
                                            .addGroup(gl_panel_1.createSequentialGroup()
                                                    .addComponent(lblLatitude)
                                                    .addGap(6)
                                                    .addComponent(lblVallatitude))
                                            .addGroup(gl_panel_1.createSequentialGroup()
                                                    .addComponent(lblAltitude)
                                                    .addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(lblValaltitude))
                                            .addGroup(gl_panel_1.createSequentialGroup()
                                                    .addComponent(lblVitesse)
                                                    .addGap(6)
                                                    .addComponent(lblValvitesse))
                                            .addGroup(gl_panel_1.createSequentialGroup()
                                                    .addComponent(lblPrcision)
                                                    .addGap(6)
                                                    .addComponent(lblValprecision))
                                            .addComponent(lblLastData)
                                            .addComponent(lblVallastdata))
                                    .addContainerGap(195, Short.MAX_VALUE))
            );
            gl_panel_1.setVerticalGroup(
                    gl_panel_1.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panel_1.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(lblProvedor)
                                            .addComponent(lblValProvedor))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(lblLongitude)
                                            .addComponent(lblVallongitude))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                            .addComponent(lblLatitude)
                                            .addComponent(lblVallatitude))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(lblAltitude)
                                            .addComponent(lblValaltitude))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                            .addComponent(lblVitesse)
                                            .addComponent(lblValvitesse))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                            .addComponent(lblPrcision)
                                            .addComponent(lblValprecision))
                                    .addGap(18)
                                    .addComponent(lblLastData)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(lblVallastdata)
                                    .addContainerGap(58, Short.MAX_VALUE))
            );
            panel_1.setLayout(gl_panel_1);
            panel.setLayout(gl_panel);
            setLayout(groupLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void centerMapView() {
        mapViewer.setDisplayPositionByLatLon(lastLatitude, lastLongitude, mapViewer.getZoom());
    }

    public void limpiarMarcadores() {
        List<MapMarker> markerList = new ArrayList<>();
        mapViewer.setMapMarkerList(markerList);
        mapViewer.setMapMarkerVisible(true);
    }

    public void agregarMarcador(double longitude, double latitude) {
        lastLatitude = latitude;
        lastLongitude = longitude;
        MapMarkerDot marker = new MapMarkerDot(latitude, longitude);
        List<MapMarker> markerList = mapViewer.getMapMarkerList();

        if (markerList == null) {
            markerList = new ArrayList<>();
        }

        markerList.add(marker);
        mapViewer.setMapMarkerList(markerList);
        mapViewer.setMapMarkerVisible(true);

        centerMapView();
    }

    public void updateMap(double longitude, double latitude, double altitude, float speed, float accuracy, long fechaLong, String provedor) {
        lastLatitude = latitude;
        lastLongitude = longitude;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        MapMarkerDot marker = new MapMarkerDot(latitude, longitude);
        List<MapMarker> markerList = new ArrayList<>();
        markerList.add(marker);
        mapViewer.setMapMarkerList(markerList);
        mapViewer.setMapMarkerVisible(true);
        lblVallongitude.setText(String.valueOf(longitude));
        lblVallatitude.setText(String.valueOf(latitude));
        lblValaltitude.setText(String.valueOf(altitude));
        lblValvitesse.setText(String.valueOf(speed));
        lblValprecision.setText(String.valueOf(accuracy));
        lblValProvedor.setText(provedor);
//        Date date = new Date(System.currentTimeMillis());
//        lblVallastdata.setText(date.toString());
        Date fecha = new Date(fechaLong);
        lblVallastdata.setText(sdf.format(fecha));
        centerMapView();
    }
}
